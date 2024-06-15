package com.bty.blog.service;

/**
 * @author bty
 * @date 2022/11/30
 * @since 1.8
 **/
public interface TokenService {

    String initToken(Object user);

    Object verifyToken(String jwt);

}
