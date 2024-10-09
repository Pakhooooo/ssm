package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "t_role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BasePO {
    
    @Id
    private Integer id;

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    @NotNull(message = "权限ID不能为空")
    private Integer permissionId;
    
}
