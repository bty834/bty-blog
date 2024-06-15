package com.bty.blog.controller;

import com.bty.blog.domain.PageResponse;
import com.bty.blog.util.PageUtil;

import java.util.List;

/**
 * @author bty
 * @date 2022/12/10
 * @since 1.8
 **/
public abstract class BaseController {

    protected void helpPage(){
        PageUtil.helpPage();
    }

    protected PageResponse pageResponse(List<?> list){
        return PageUtil.pageResponse(list);
    }
    protected void clearPage(){
        PageUtil.clearPage();
    }
}
