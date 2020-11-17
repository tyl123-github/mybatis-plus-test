package com.tyl.common.response;


import com.tyl.common.enums.ResultCode;

/**
 * 自定义返回工具类
 *
 * @param <T> 实体泛型
 */
public class ApiResponse<T> {


    public static final Integer COUNT = 0;

    public static <T> Response<T> success(T data) {
        return success(data, COUNT);
    }

    public static <T> Response<T> success(T data, Integer count) {
        return response(ResultCode.SUCCESS.getCode(), data, ResultCode.SUCCESS.getMessage(), count);
    }

    public static <T> Response<T> success(String code, T data) {
        return response(code, data, ResultCode.SUCCESS.getMessage(), COUNT);
    }

    public static <T> Response<T> response(String code, T data, String msg, Integer count) {
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMsg(msg);
        response.setDate(data);
        response.setCount(count);
        return response;
    }

    public static Response response(String code, Object data, String msg) {
        return response(code, data, msg, COUNT);
    }

    public static Response response(String code, Object data) {
        return response(code, data, ResultCode.DEFAULT.getMessage(), COUNT);
    }

    public static <T> Response<T> error() {
        return error(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage());
    }

    public static <T> Response<T> error(String code, String msg) {
        return response(code, null, msg, COUNT);
    }

    public static Response error(String code) {
        return error(code, ResultCode.DEFAULT.getMessage());
    }
}
