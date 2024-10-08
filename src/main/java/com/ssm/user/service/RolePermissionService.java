package com.ssm.user.service;

import com.ssm.user.dto.RolePermissionDTO;

public interface RolePermissionService {
    
    int addRolePermission(RolePermissionDTO rolePermissionDTO);
    
    int deleteRolePermission(RolePermissionDTO rolePermissionDTO);
    
    int updateRolePermission(RolePermissionDTO rolePermissionDTO);
    
    
    
}
