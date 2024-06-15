package com.bty.blog.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author bty
 * @date 2022/12/6
 * @since 1.8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("博文修改")
public class PostEditDTO {
    private Integer id;
    private String title;
    private String excerpt;
    private String content;
    private String coverUrl;
    private List<Integer> tagIdList;
}
