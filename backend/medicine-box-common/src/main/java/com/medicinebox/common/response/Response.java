package com.medicinebox.common.response;

import lombok.Data;

/**
 * 统一响应结构
 */
@Data
public class Response<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 构造方法
     * @param code 状态码
     * @param message 消息
     * @param data 数据
     */
    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应
     * @param data 数据
     * @param <T> 数据类型
     * @return 响应对象
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "success", data);
    }

    /**
     * 成功响应
     * @param <T> 数据类型
     * @return 响应对象
     */
    public static <T> Response<T> success() {
        return new Response<>(200, "success", null);
    }

    /**
     * 失败响应
     * @param code 状态码
     * @param message 消息
     * @param <T> 数据类型
     * @return 响应对象
     */
    public static <T> Response<T> fail(Integer code, String message) {
        return new Response<>(code, message, null);
    }

    /**
     * 失败响应
     * @param message 消息
     * @param <T> 数据类型
     * @return 响应对象
     */
    public static <T> Response<T> fail(String message) {
        return new Response<>(500, message, null);
    }
}
