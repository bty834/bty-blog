package com.bty.blog.config;

import com.bty.blog.dao.UserMapper;
import com.bty.blog.entity.User;
import com.bty.blog.filter.MultiReadFilter;
import com.bty.blog.interceptor.ThrottleInterceptor;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.boot.starter.autoconfigure.SwaggerUiWebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KaptchaProperties.class)
//@EnableWebMvc 使用会使knife4j失效，需要重新定义resourcehandler
public class WebConfig implements WebMvcConfigurer{

    private final RedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThrottleInterceptor(redisTemplate));
    }

    @Bean
    public Config kaptchaConfig(KaptchaProperties kp){
        Properties p = new Properties();
        p.setProperty(Constants.KAPTCHA_BORDER,kp.getBorder()?"yes":"no");
        p.setProperty(Constants.KAPTCHA_IMAGE_WIDTH,kp.getWidth());
        p.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT,kp.getHeight());
        p.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,kp.getCharLength());
        p.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES,kp.getFont());
        p.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE,kp.getFontSize());
        return new Config(p);
    }

    @Bean
    public DefaultKaptcha defaultKaptcha(Config config){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }



    /**
//     * 跨域配置
//     */
    @Bean
    public CorsFilter corsFilter()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        // 设置访问源地址
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // 返回新的CorsFilter
        return new CorsFilter(source);
    }

    @Bean
    public FilterRegistrationBean<MultiReadFilter> multiReadFilterRegistration() {
        FilterRegistrationBean<MultiReadFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MultiReadFilter());
        registration.addUrlPatterns("/*");
        registration.setName("multiReadFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }


}
