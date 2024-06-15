package com.bty.blog.dao;

import com.bty.blog.entity.Timeline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
public interface TimelineMapper {



    List<Timeline> selectTimelineList();

    void createTimeline(@Param("timeline")Timeline timeline);

    void updateTimeline(@Param("timeline")Timeline timeline);

    void deleteTimelineById(@Param("timelineId")Integer timelineId);

}
