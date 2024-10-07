package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.RoleDTO;
import com.ssm.user.dto.RoleListDTO;
import com.ssm.user.po.Role;
import com.ssm.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/role/add")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('role:add')")
    public Result addRole(@RequestBody Role role) {
        int flag = roleService.addRole(role);
        if (flag == 0) {
            Result.error("新增角色失败");
        }

        return Result.success("新增角色成功");
    }

    @DeleteMapping(value = "/role/{roleId}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('role:delete')")
    public Result deleteRole(@PathVariable int roleId) {
        int flag = roleService.deleteRole(roleId);
        if (flag == 0) {
            Result.error("删除角色失败");
        }

        return Result.success("删除角色成功");
    }

    @PutMapping(value = "/role/edit")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('role:edit')")
    public Result editRole(@RequestBody RoleDTO roleDTO) {
        int flag = roleService.editRole(roleDTO);
        if (flag == 0) {
            Result.error("修改角色失败");
        }

        return Result.success("修改角色成功");
    }

    @PostMapping(value = "/roles")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('role:list')")
    public Result getRoles(@RequestBody RoleListDTO roleListDTO) {
        return Result.success(roleService.getRoles(roleListDTO), "角色列表查询成功");
    }
    
}
