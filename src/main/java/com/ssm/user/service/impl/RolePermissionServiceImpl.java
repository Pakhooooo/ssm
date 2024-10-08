package com.ssm.user.service.impl;

import com.ssm.user.dto.RolePermissionDTO;
import com.ssm.user.mapper.RolePermissionMapper;
import com.ssm.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public void setRolePermissionMapper(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public int addRolePermission(RolePermissionDTO rolePermissionDTO) {
        return 0;
    }

    @Override
    public int deleteRolePermission(RolePermissionDTO rolePermissionDTO) {
        return 0;
    }

    @Override
    public int updateRolePermission(RolePermissionDTO rolePermissionDTO) {
        return 0;
    }
}
