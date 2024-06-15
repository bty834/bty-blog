package com.bty.blog.service.impl;

import com.bty.blog.config.KaptchaProperties;
import com.bty.blog.domain.Response;
import com.bty.blog.entity.vo.CaptchaVO;
import com.bty.blog.service.CaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author bty
 * @date 2022/12/13
 * @since 1.8
 **/
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    private final KaptchaProperties kaptchaProperties;
    private final DefaultKaptcha defaultKaptcha;

    private final RedisTemplate redisTemplate;

    private static final String CAPTCHA_KEY = "CAPTCHA:";

    public static String getCaptchaRedisKey(String uuid) {
        return CAPTCHA_KEY + uuid;
    }


    @Override
    public CaptchaVO getCaptcha() {
        if (!kaptchaProperties.getEnabled()) {
            return new CaptchaVO(kaptchaProperties.getEnabled(), null, null, new Date(System.currentTimeMillis()));
        }

        String text = defaultKaptcha.createText();

        String uuid = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set(getCaptchaRedisKey(uuid), text, kaptchaProperties.getExpireSeconds(), TimeUnit.SECONDS);

        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpg", stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String encodedImage = new String(Base64.encodeBase64(stream.toByteArray()), StandardCharsets.UTF_8);

        return new CaptchaVO(kaptchaProperties.getEnabled(), uuid, encodedImage, new Date(System.currentTimeMillis()));
    }

    @Override
    public void verifyCaptcha(String uuid,String text) {
        String answer = (String)redisTemplate.opsForValue().get(getCaptchaRedisKey(uuid));
        if(answer==null ){
            LOGGER.error("captcha expired");
            throw new RuntimeException("captcha expired");
        }
        if(!answer.equals(text)){
            LOGGER.error("captcha not matched");
            throw new RuntimeException("captcha not matched");
        }
    }
}
