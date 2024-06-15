package com.bty.blog.annotation;

import com.bty.blog.domain.LimitType;

import java.lang.annotation.*;

/**
 * @author bty
 * @date 2022/12/12
 * @since 1.8
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit
{

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 30;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
