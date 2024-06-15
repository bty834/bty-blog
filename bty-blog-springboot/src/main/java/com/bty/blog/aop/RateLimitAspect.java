package com.bty.blog.aop;

import com.bty.blog.annotation.RateLimit;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author bty
 * @date 2022/12/12
 * @since 1.8
 **/
@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    public static final String LIMIT_PREFIX="RATE_LIMIT:";

    private final RedisTemplate<Object,Object> redisTemplate;

    private final RedisScript<Long> limitScript;

    @Before("@annotation(rateLimit)")
    public void beforeController(JoinPoint point, RateLimit rateLimit) {

        doLimit(point, rateLimit);
    }

    private void doLimit(JoinPoint point, RateLimit rateLimit){
        String key = generateKey(point);
        Long number = redisTemplate.execute(limitScript, Collections.singletonList(key), rateLimit.count(), rateLimit.time());
        if (Objects.isNull(number) || number > rateLimit.count())
        {
            throw new RuntimeException("访问过于频繁，请稍候再试");
        }

    }

    public static String generateKey(JoinPoint point){

        Signature signature = point.getSignature();
        return LIMIT_PREFIX + signature.toShortString();
    }
}
