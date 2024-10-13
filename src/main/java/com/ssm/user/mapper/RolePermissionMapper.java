package com.ssm.user.mapper;

import com.ssm.user.po.RolePermission;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.Set;

public interface RolePermissionMapper extends Mapper<RolePermission>, InsertListMapper<RolePermission> {
    
    Set<Integer> getPermissionIdsByRoleId(Integer roleId);
    
    int deleteRolePermissionByRoleId(Integer roleId);
    
}
