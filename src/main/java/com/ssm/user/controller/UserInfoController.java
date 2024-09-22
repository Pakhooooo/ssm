package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.UserDTO;
import com.ssm.user.service.UserInfoService;
import com.ssm.user.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
public class UserInfoController {
    
    private UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Operation(summary = "查询用户信息")
    @GetMapping(value = "/user/info/{userId}")
    public Result getUserInfo(
            @Parameter(description = "用户ID", example = "1", required = true)
            @PathVariable @NotNull(message = "用户ID不能为空") int userId) {
        UserVO userVO = userInfoService.getUserInfoById(userId);
        return Result.success(userVO, "用户信息查询成功");
    }
    
    @PutMapping(value = "/user/password/change")
    public Result changePassword(@RequestBody UserDTO userDTO) {
        
        return Result.success(new JSONObject(), "密码修改成功");
    }

    @PutMapping(value = "/user/info/change")
    public Result changeInformation(@RequestBody UserDTO userDTO) {
        
        return Result.success(new JSONObject(), "个人信息修改成功");
    }
    
    @GetMapping(value = "/user/list")
    public Result getUserList() {
        
        return Result.success(new JSONObject(), "用户列表查询成功");
    }
    
    @DeleteMapping("/user/delete")
    public Result delUser(@RequestBody UserDTO userDTO) {
        
        return Result.success(new JSONObject(), "删除用户成功");
    }
    
}

