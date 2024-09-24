package com.ssm.user.po;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseEntity {
    
    private Integer id;
    
    private Integer roleId;
    
    private String roleName;
    
    private Integer permissionId;
    
    private String permissionName;
    
}
