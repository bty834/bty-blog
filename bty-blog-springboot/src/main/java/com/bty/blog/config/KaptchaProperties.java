package com.bty.blog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bty
 * @date 2022/12/12
 * @since 1.8
 **/

@ConfigurationProperties("captcha")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KaptchaProperties {

    private Boolean enabled ;
    private Boolean border;
    private String width ;
    private String height;
    private String fontSize;
    private String charLength;
    private String font;
    private Integer expireSeconds;
}
