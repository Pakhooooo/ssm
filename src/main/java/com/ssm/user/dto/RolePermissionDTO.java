package com.ssm.user.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RolePermissionDTO {
    
    private Integer roleId;
    
    private Set<Integer> permissionIds;
    
}
