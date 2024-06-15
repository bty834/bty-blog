package com.bty.blog.service.impl;

import com.bty.blog.dao.UserMapper;
import com.bty.blog.entity.User;
import com.bty.blog.entity.dto.LoginDTO;
import com.bty.blog.service.CaptchaService;
import com.bty.blog.service.LoginService;
import com.bty.blog.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


import static com.bty.blog.util.CodecUtil.encryptBySHA256;

/**
 * @author bty
 * @date 2022/12/2
 * @since 1.8
 **/
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Value("${password.secret}")
    private String salt;


    private final UserMapper userMapper;

    private final TokenService tokenService;

    private final CaptchaService captchaService;

    @Override
    public String startLogin(LoginDTO loginDTO) {
        // verifyCaptcha
        captchaService.verifyCaptcha(loginDTO.getUuid(), loginDTO.getCaptcha());

        // verify username password
        User user = userMapper.selectUserByUsername(loginDTO.getUsername());
        if (user == null) {
            LOGGER.error("user:{} not exists",loginDTO.getUsername());
            throw new RuntimeException("user not exists");
        }


        String decodedPassword = new String(Base64.getDecoder().decode(loginDTO.getPassword()), StandardCharsets.UTF_8);

        if (!user.getPassword().equals(encryptBySHA256(decodedPassword, salt))) {
            LOGGER.error("user:{} password not right",loginDTO.getUsername());
            throw new RuntimeException("password not right");
        }
        // generate jwt
        return tokenService.initToken(user);
    }


}
