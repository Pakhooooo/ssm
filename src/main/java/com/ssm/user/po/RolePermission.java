package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BasePO {
    
    @Id
    private Integer id;
    
    private Integer roleId;
    
    private Integer permissionId;
    
}
