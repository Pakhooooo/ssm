package com.ssm.user.service.impl;

import com.ssm.user.dto.RolePermissionDTO;
import com.ssm.user.mapper.RolePermissionMapper;
import com.ssm.user.po.RolePermission;
import com.ssm.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public void setRolePermissionMapper(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public int saveRolePermission(RolePermissionDTO rolePermissionDTO) {
        Integer roleId = rolePermissionDTO.getRoleId();
        List<Integer> permissionIds = rolePermissionDTO.getPermissionIds();
        if (permissionIds.isEmpty()) {
            return rolePermissionMapper.deleteRolePermissionByRoleId(roleId);
        }

        List<Integer> originalPermissions = rolePermissionMapper.getPermissionIdsByRoleId(roleId);
        if (originalPermissions.isEmpty()) {
            List<RolePermission> rolePermissions = getRolePermissions(roleId, permissionIds);
            return rolePermissionMapper.insertList(rolePermissions);
        }

        List<Integer> updatePermissionIds = permissionIds.stream()
                .filter(permissionId -> !originalPermissions.contains(permissionId))
                .collect(Collectors.toList());
        
        if (updatePermissionIds.isEmpty()) {
            return 1;
        }

        List<RolePermission> rolePermissions = getRolePermissions(roleId, updatePermissionIds);
        return rolePermissionMapper.insertList(rolePermissions);
    }
    
    private List<RolePermission> getRolePermissions(Integer roleId, List<Integer> permissionIds) {
        return permissionIds.stream().map(permissionId -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            return rolePermission;
        }).collect(Collectors.toList());
    }

}
