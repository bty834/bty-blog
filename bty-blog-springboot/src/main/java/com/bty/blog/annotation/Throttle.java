package com.bty.blog.annotation;

import java.lang.annotation.*;

/**
 * prevent repeated form submit.
 * it only works with application/json type
 * because this implementation in {@link com.bty.blog.filter.MultiReadFilter}
 * only application/json {@link javax.servlet.http.HttpServletRequest} allowed to be wrapped as
 * {@link com.bty.blog.wrapper.MultiReadRequestWrapper}
 * and {@link com.bty.blog.interceptor.ThrottleInterceptor} only process with {@link com.bty.blog.wrapper.MultiReadRequestWrapper}
 * @author bty
 * @date 2022/12/15
 * @since 1.8
 **/
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Throttle {

    // ms
    int interval() default 5000;
}
