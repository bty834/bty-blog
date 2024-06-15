package com.bty.blog.exception;

import com.bty.blog.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bty
 * @date 2022/12/3
 * @since 1.8
 **/
@RestControllerAdvice
public class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Response exceptionHander(Exception e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        LOGGER.error("请求地址'{}',发生系统异常.", requestURI, e);
        return Response.error(e.getMessage());
    }

}
