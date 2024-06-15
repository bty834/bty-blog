package com.bty.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author bty
 * @date 2022/12/10
 * @since 1.8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResponse implements Serializable {

    private static final long serialVersionUID = 2L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> data;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;


    public static PageResponse success(List<?> list,long total)
    {
        return new PageResponse(total,list,200,"操作成功");
    }
}
