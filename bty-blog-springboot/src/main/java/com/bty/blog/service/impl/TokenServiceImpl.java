package com.bty.blog.service.impl;

import com.bty.blog.entity.User;
import com.bty.blog.service.TokenService;
import com.bty.blog.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author bty
 * @date 2022/11/30
 * @since 1.8
 **/
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    public static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${token.expire-minutes}")
    private Integer expireMinutes;

    private final JwtUtil jwtUtil;
    private final RedisTemplate redisTemplate;
    private String getTokenRedisKey(String uuid) {
        return "LOGIN:" + uuid;
    }
    @Override
    public String initToken(Object user) {
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(getTokenRedisKey(uuid), user, expireMinutes, TimeUnit.MINUTES);
        return jwtUtil.encodeUUID(uuid);
    }

    @Override
    public Object verifyToken(String jwt) {
        String uuid = jwtUtil.decodeUUID(jwt);
        User user  = (User)redisTemplate.opsForValue().get(getTokenRedisKey(uuid));
        redisTemplate.expire(getTokenRedisKey(uuid) ,expireMinutes, TimeUnit.MINUTES);
        if(user==null){
            LOGGER.error("user not login");
            throw new RuntimeException("user not login");
        }
        return user;
    }


}
