package com.bty.blog.controller;

import com.bty.blog.BlogApplication;
import com.bty.blog.annotation.Admin;
import com.bty.blog.annotation.Throttle;
import com.bty.blog.domain.Response;
import com.bty.blog.entity.Timeline;
import com.bty.blog.service.TimelineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
@Api(value = "博客api")
@RestController
@RequiredArgsConstructor
public class TimelineController {

    private final TimelineService timelineService;

    @ApiOperation(value = "查询首页timeline列表")
    @GetMapping("/timeline")
    public Response getTimelineList(){
        return Response.success(timelineService.selectTimelineList());
    }


    @Admin
    @ApiOperation(value = "添加timeline")
    @PostMapping("/timeline")
    public Response createTimeline(@RequestBody Timeline timeline){
        timelineService.createTimeline(timeline);
        return Response.success();
    }

    @Admin
    @ApiOperation(value = "修改timeline")
    @PutMapping("/timeline")
    public Response updateTimeline(@RequestBody Timeline timeline){
        timelineService.updateTimeline(timeline);
        return Response.success();
    }

    @Admin
    @ApiOperation(value = "删除timeline")
    @DeleteMapping("/timeline/{timelineId}")
    public Response deleteTimeline(@PathVariable Integer timelineId){
        timelineService.deleteTimelineById(timelineId);
        Runtime.getRuntime().availableProcessors();
        return Response.success();
    }
}
