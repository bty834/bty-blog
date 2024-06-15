package com.bty.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
@ApiModel(value = "评论")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentVO {
    private Integer id;
    private String name;
    private String content;
    @ApiParam(value = "创建时间，格式yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
}
