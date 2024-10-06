package com.ssm.common.handler;

import com.ssm.common.exception.AuthFailureException;
import com.ssm.common.global.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.acl.NotOwnerException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理特定的异常
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException ex) {
        return Result.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    // 处理所有其他异常
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    // 捕获 @Valid 注解引发的参数校验异常
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
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
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result> handleAuthenticationException(Exception ex) {
        log.error(ex.getMessage());
        Result result = new Result("您还没有登录，请登录后重试", HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthFailureException.class)
    public Result handleAuthFailureException(Exception ex) {
        return Result.error(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ResponseBody
    @ExceptionHandler({NotOwnerException.class, org.springframework.security.access.AccessDeniedException.class})
    public ResponseEntity<Result> handleNotOwnerException(Exception ex) {
        log.error(ex.getMessage(), ex);
        Result result = new Result("您没有权限进行操作", HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

}
