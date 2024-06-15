package com.bty.blog.annotation;

import java.lang.annotation.*;

/**
 * @author bty
 * @date 2022/12/2
 * @since 1.8
 *
 * Controller annotated with this would need to validate token
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Admin {
}
