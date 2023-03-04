package com.example.jwt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private String token;
    private Object data;

    /**
     * 请求成功的方法
     */
    public static Result success(String msg, String token) {
        return new Result(Constants.CODE_200, msg, token, null);
    }

    /**
     * 请求成功的方法, 并且返回数据
     */
    public static Result success(String msg, String token, Object data) {
        return new Result(Constants.CODE_200, msg, token, data);
    }

    /**
     * 请求失败的方法
     */
    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null, null);
    }

    /**
     * 请求失败的方法
     */
    public static Result error(String msg) {
        return new Result(Constants.CODE_500, msg, null, null);
    }

}
