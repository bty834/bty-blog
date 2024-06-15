package com.bty.blog.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author bty
 * @date 2022/12/7
 * @since 1.8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("记录添加")
public class RecordCreateDTO {

    private String url;
    private String coverUrl;
    private String title;
    private String description;
    private Integer type;
    private List<Integer> collectionIdList;
}
