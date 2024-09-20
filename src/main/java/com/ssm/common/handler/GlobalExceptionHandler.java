package com.ssm.common.handler;

import com.ssm.common.global.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理特定的异常
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return Result.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    // 处理所有其他异常
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error("Internal server error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    // 捕获 @Valid 注解引发的参数校验异常
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 遍历所有校验失败的字段
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.put("message", errorMessage);
        });

        // 返回自定义的错误信息
        return Result.error(errors.get("message"), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class) 
    public Result<String> handleAccessDeniedException(AccessDeniedException e) {
        // 返回自定义的提示信息
        log.error(e.getMessage(), e);
        return Result.error("您还未登录，请登录后重试", HttpStatus.FORBIDDEN.value());
    }

}