package com.bty.blog.service;

import com.bty.blog.entity.Timeline;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
public interface TimelineService {

    List<Timeline> selectTimelineList();


    void createTimeline(Timeline timeline);

    void updateTimeline(Timeline timeline);

    void deleteTimelineById(Integer timelineId);
}
