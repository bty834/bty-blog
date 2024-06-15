package com.bty.blog.controller;

import com.bty.blog.annotation.Throttle;
import com.bty.blog.domain.Response;
import com.bty.blog.entity.Test;
import com.bty.blog.entity.dto.LoginDTO;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author bty
 * @date 2022/12/15
 * @since 1.8
 **/
@Api(value = "测试")
@RestController
@RequiredArgsConstructor
public class TestController {


    @Throttle(interval = 500000)
    @ApiOperation(value = "测试防重")
    @PostMapping("/test")
    public Response login(@RequestBody Test test){
        System.out.println(test);
        return Response.success(Collections.singletonMap("hello","success"));
    }
}
