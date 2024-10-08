package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.UserRoleDTO;
import com.ssm.user.po.UserRole;
import com.ssm.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRoleController {
    
    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/user/role/add")
    public Result addUserRole(@RequestBody UserRole userRole) {
        int flag = userRoleService.addUserRole(userRole);
        if (flag == 0) {
            Result.error("新增用户角色失败");
        }

        return Result.success("新增用户角色成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/user/role/{userRoleId}")
    public Result deleteUserRole(@PathVariable int userRoleId) {
        int flag = userRoleService.deleteUserRole(userRoleId);
        if (flag == 0) {
            Result.error("删除用户角色失败");
        }

        return Result.success("删除用户角色成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/user/role/edit")
    public Result editUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        int flag = userRoleService.editUserRole(userRoleDTO);
        if (flag == 0) {
            Result.error("修改用户角色失败");
        }

        return Result.success("修改用户角色成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/user/roles")
    public Result getRoles() {
        return Result.success(userRoleService.getUserRoles(), "用户角色列表查询成功");
    }
    
}
