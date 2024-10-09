package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Table(name = "t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BasePO {

    @Id
    private Integer id;

    @NotBlank(message = "角色标识不能为空")
    private String roleCode;

    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    private List<Permission> permissions;
    
}
