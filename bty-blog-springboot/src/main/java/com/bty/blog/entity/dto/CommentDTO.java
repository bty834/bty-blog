package com.bty.blog.entity.dto;

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
@ApiModel("评论提交体")
public class CommentDTO {
    private String name;
    private String email;
    private String content;
    private Integer postId;
}
