package com.bty.blog.aop;

import com.bty.blog.entity.User;
import com.bty.blog.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bty
 * @date 2022/12/2
 * @since 1.8
 **/
@Aspect
@Component
@RequiredArgsConstructor
public class AdminAuthAspect {

    private final TokenService tokenService;
    private final HttpServletRequest request;

    @Before("@annotation(com.bty.blog.annotation.Admin)")
    public void beforeController(JoinPoint point) {
        validateToken();
    }


    private void validateToken() {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(auth==null){
            throw new RuntimeException("pls login first");
        }
        if (!auth.startsWith("Bearer ")) {
            throw new RuntimeException("not Bearer token");
        }
        String jwtToken = auth.substring(7);
        tokenService.verifyToken(jwtToken);
    }


}
