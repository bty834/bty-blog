package com.bty.blog.controller;

import com.bty.blog.annotation.RateLimit;
import com.bty.blog.config.KaptchaProperties;
import com.bty.blog.domain.Response;
import com.bty.blog.entity.dto.LoginDTO;
import com.bty.blog.entity.vo.CaptchaVO;
import com.bty.blog.service.CaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author bty
 * @date 2022/12/12
 * @since 1.8
 **/
@Api(value = "验证码")
@RestController()
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaService captchaService;

    @ApiOperation(value = "获取验证码")
    @GetMapping()
    public Response getCaptcha(){
        return Response.success(captchaService.getCaptcha());
    }

}
