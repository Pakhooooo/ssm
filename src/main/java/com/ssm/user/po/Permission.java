package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Table(name = "t_permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BasePO {

    @Id
    private Integer id;

    @NotBlank(message = "权限名称不能为空")
    private String permissionName;

    @NotBlank(message = "权限Key不能为空")
    private String permissionKey;
    
    private String permissionType;
    
}
