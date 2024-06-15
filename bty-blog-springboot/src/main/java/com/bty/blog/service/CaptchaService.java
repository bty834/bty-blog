package com.bty.blog.service;

import com.bty.blog.entity.vo.CaptchaVO;

/**
 * @author bty
 * @date 2022/12/13
 * @since 1.8
 **/
public interface CaptchaService {


    CaptchaVO getCaptcha();

    void verifyCaptcha(String uuid,String text);
}
