package com.ssm.user.service.impl;

import com.ssm.user.dto.RolePermissionDTO;
import com.ssm.user.mapper.RolePermissionMapper;
import com.ssm.user.po.RolePermission;
import com.ssm.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public void setRolePermissionMapper(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRolePermission(RolePermissionDTO rolePermissionDTO) {
        Integer roleId = rolePermissionDTO.getRoleId();
        Set<Integer> permissionIds = rolePermissionDTO.getPermissionIds();
        if (permissionIds.isEmpty()) {
            return rolePermissionMapper.deleteRolePermissionByRoleId(roleId);
        }

        Set<Integer> oldPermissions = rolePermissionMapper.getPermissionIdsByRoleId(roleId);
        if (oldPermissions.isEmpty()) {
            List<RolePermission> rolePermissions = getRolePermissions(roleId, permissionIds);
            return rolePermissionMapper.insertList(rolePermissions);
        }

        rolePermissionMapper.deleteRolePermissionByRoleId(roleId);

        List<RolePermission> rolePermissions = getRolePermissions(roleId, permissionIds);
        return rolePermissionMapper.insertList(rolePermissions);
    }
    
    private List<RolePermission> getRolePermissions(Integer roleId, Set<Integer> permissionIds) {
        return permissionIds.stream().map(permissionId -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermission.setCreateTime(new Date());
            rolePermission.setUpdateTime(new Date());
            rolePermission.setDelStatus(0);
            return rolePermission;
        }).collect(Collectors.toList());
    }

}
