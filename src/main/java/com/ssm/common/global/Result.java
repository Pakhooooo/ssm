package com.ssm.common.global;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Result<T> {

    private boolean success;
    private T data;
    private String message;
    private int status;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message, int status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }

    public Result(boolean success, T data, String message, int status) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.status = status;
    }

    // 静态方法用于成功响应
    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null, HttpStatus.OK.value());
    }

    // 静态方法用于成功响应，带消息
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, data, message, HttpStatus.OK.value());
    }

    // 静态方法用于失败响应
    public static <T> Result<T> error(String message, int status) {
        return new Result<>(false, message, status);
    }
    
}
