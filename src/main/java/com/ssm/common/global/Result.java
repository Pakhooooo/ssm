package com.ssm.common.global;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result(String message) {
        this.message = message;
    }

    public Result(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public Result(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static Result<String> success(String message) {
        return new Result<>(message);
    }

    // 静态方法用于成功响应，带消息
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(data, message, HttpStatus.OK.value());
    }

    // 静态方法用于失败响应
    public static <T> Result<T> error(String message, int code) {
        return new Result<>(message, code);
    }
    
}
