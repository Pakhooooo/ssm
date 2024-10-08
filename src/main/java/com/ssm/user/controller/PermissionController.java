package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.PermissionDTO;
import com.ssm.user.dto.PermissionListDTO;
import com.ssm.user.po.Permission;
import com.ssm.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController {
    
    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/permissionId/add")
    public Result addRole(@RequestBody Permission permission) {
        int flag = permissionService.addPermission(permission);
        if (flag == 0) {
            Result.error("新增权限失败");
        }

        return Result.success("新增权限成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/permission/{permissionId}")
    public Result deleteRole(@PathVariable int permissionId) {
        int flag = permissionService.deletePermission(permissionId);
        if (flag == 0) {
            Result.error("删除权限失败");
        }

        return Result.success("删除权限成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/permission/update")
    public Result updateRole(@RequestBody PermissionDTO permissionDTO) {
        int flag = permissionService.updatePermission(permissionDTO);
        if (flag == 0) {
            Result.error("修改权限失败");
        }

        return Result.success("修改权限成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/permissions")
    public Result getRoles(@RequestBody PermissionListDTO permissionListDTO) {
        return Result.success(permissionService.getPermissions(permissionListDTO), "权限列表查询成功");
    }
    
}
