package com.bty.blog.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private  int code;
    private  String msg;
    private  Object data;

    public static <T> Response<T> success()
    {
        return Response.success("操作成功");
    }

    public static <T> Response<T> success(Object data)
    {
        return Response.success("操作成功", data);
    }

    public static <T> Response<T> success(String msg)
    {
        return Response.success(msg, null);
    }

    public static <T> Response<T> success(String msg, Object data)
    {
        return new Response(200, msg, data);
    }

    public static <T> Response<T> error(String msg)
    {
        return Response.error(msg,null);
    }


    public static <T> Response<T> error(String msg, T data)
    {
        return new Response<T>(500, msg, data);
    }
}
