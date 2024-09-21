package com.ssm.common.global;

import lombok.Data;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

@Data
public class Result {

    private int code;
    private String message;
    private JSONObject data;

    public Result(String message, int code) {
        this.message = message;
        this.code = code;
        this.data = new JSONObject();
    }

    public Result(JSONObject data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static Result success(String message) {
        return new Result(message, HttpStatus.OK.value());
    }

    // 静态方法用于成功响应
    public static Result success(JSONObject data, String message) {
        return new Result(data, message, HttpStatus.OK.value());
    }

    // 静态方法用于失败响应
    public static Result error(String message, int code) {
        return new Result(new JSONObject(), message, code);
    }

}
