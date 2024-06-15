package com.bty.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author bty
 * @date 2022/11/29
 * @since 1.8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecordCollection {
    private Integer id;
    private Integer recordId;
    private Integer collectionId;
}
