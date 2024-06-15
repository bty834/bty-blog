package com.bty.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author bty
 * @date 2022/11/29
 * @since 1.8
 **/
@ApiModel(value = "评论")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CollectionVO {
    private Integer id;
    private String name;
}
