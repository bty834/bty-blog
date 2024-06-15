package com.bty.blog.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "首页时间线信息")
public class Timeline {
    private Integer id;
    private String phase;
    private String title;
    private String link;
    private String subTitle;
    private String content;
    private String start;
    private String end;
}
