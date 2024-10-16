package com.ssm.common.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Data
public class Result {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息提醒
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public Result(String message, int code) {
        this.message = message;
        this.code = code;
        this.data = new ObjectMapper().createObjectNode();
    }

    public Result(Object data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static Result success(String message) {
        return new Result(message, HttpStatus.OK.value());
    }

    // 静态方法用于成功响应
    public static Result success(Object data, String message) {
        if (Objects.isNull(data)) {
            data = new ObjectMapper().createObjectNode();
        }
        return new Result(data, message, HttpStatus.OK.value());
    }

    public static Result error(String message) {
        return new Result(new ObjectMapper().createObjectNode(), message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    // 静态方法用于失败响应
    public static Result error(String message, int code) {
        return new Result(new ObjectMapper().createObjectNode(), message, code);
    }

}
