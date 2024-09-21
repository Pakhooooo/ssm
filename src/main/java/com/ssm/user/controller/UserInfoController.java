package com.ssm.user.controller;

import com.ssm.common.global.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
public class UserInfoController {

    @Operation(summary = "查询用户信息")
    @GetMapping(value = "/user/info/{userId}")
    public Result getUserInfo(
            @Parameter(description = "用户ID", example = "1", required = true)
            @PathVariable @NotNull(message = "用户ID不能为空") int userId) {
        
        return Result.success(null, "查询成功");
    }
    
}

