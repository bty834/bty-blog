package com.bty.blog.service.impl;

import com.bty.blog.dao.TimelineMapper;
import com.bty.blog.entity.Timeline;
import com.bty.blog.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
@Service
@RequiredArgsConstructor
public class TimelineServiceImpl implements TimelineService {


    public static final Logger LOGGER = LoggerFactory.getLogger(TimelineServiceImpl.class);


    private final TimelineMapper timelineMapper;


    @Override
    public List<Timeline> selectTimelineList() {
        return timelineMapper.selectTimelineList();
    }

    @Override
    public void createTimeline(Timeline timeline) {
        LOGGER.info("create timeline title: {}",timeline.getTitle());
        timelineMapper.createTimeline(timeline);
    }

    @Override
    public void updateTimeline(Timeline timeline) {
        LOGGER.info("update timeline title: {}",timeline.getTitle());

        timelineMapper.updateTimeline(timeline);
    }

    @Override
    public void deleteTimelineById(Integer timelineId) {
        LOGGER.warn("delete timelineId:{}",timelineId);
        timelineMapper.deleteTimelineById(timelineId);
    }
}
