package com.bty.blog.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.bty.blog.annotation.Throttle;
import com.bty.blog.util.ServletUtil;
import com.bty.blog.wrapper.MultiReadRequestWrapper;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * a custom handlerInterceptor must be added in {@link com.bty.blog.config.WebConfig#addInterceptors(InterceptorRegistry)}
 * or it will not work even if you add {@link Component} annotation.
 * @author bty
 * @date 2022/12/15
 * @since 1.8
 **/
//@Component not working!
@RequiredArgsConstructor
public class ThrottleInterceptor implements HandlerInterceptor {

    public static final String THROTTLE_REDIS_KEY_PREFIX = "THROTTLE:";
    private static final String CONDITION = "condition";
    private static final String TIME = "time";

    private final RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Throttle throttle = method.getAnnotation(Throttle.class);
            if (Objects.nonNull(throttle)) {
                handleThrottle(request, response, throttle);
            }
            return true;
        }
        return true;
    }

    private void handleThrottle(HttpServletRequest request, HttpServletResponse response, Throttle throttle) {
        String newBodyStr = null;

        if (request instanceof MultiReadRequestWrapper) {
            MultiReadRequestWrapper multiReadRequestWrapper = (MultiReadRequestWrapper) request;
            newBodyStr = ServletUtil.extractBodyAsString(multiReadRequestWrapper);
        }

        if (Strings.isNullOrEmpty(newBodyStr)) {
            newBodyStr = JSONUtils.toJSONString(request.getParameterMap());
        }

        Map<String, Object> newData = new HashMap<>();

        newData.put(CONDITION, newBodyStr);
        newData.put(TIME, System.currentTimeMillis());


        String uri = request.getRequestURI();

        String key = THROTTLE_REDIS_KEY_PREFIX + uri;

        Object lastRequest = redisTemplate.opsForValue().get(key);

        if (Objects.nonNull(lastRequest)) {

            Map<String, Object> lastRequestInfo = (Map<String, Object>) lastRequest;

            if (lastRequestInfo.containsKey(uri)) {
                Map<String, Object> oldData = (Map<String, Object>) lastRequestInfo.get(uri);
                if (validateTime(newData, oldData, throttle.interval()) && validateCondition(newData, oldData)) {
                    throw new RuntimeException("提交过于频繁！");
                }
            }
        }

        Map<String, Object> cacheMap = new HashMap<String, Object>();
        cacheMap.put(uri, newData);
        redisTemplate.opsForValue().set(key, cacheMap, throttle.interval(), TimeUnit.MILLISECONDS);
    }

    /**
     * 判断参数是否相同
     */
    private static boolean validateCondition(Map<String, Object> curMap, Map<String, Object> preMap) {
        String nowParams = (String) curMap.get(CONDITION);
        String preParams = (String) preMap.get(CONDITION);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private static boolean validateTime(Map<String, Object> curMap, Map<String, Object> preMap, int interval) {
        long time1 = (Long) curMap.get(TIME);
        long time2 = (Long) preMap.get(TIME);
        return (time1 - time2) < interval;
    }
}
