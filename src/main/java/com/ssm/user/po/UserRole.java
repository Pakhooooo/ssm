package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "t_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BasePO {
    
    @Id
    private Integer id;

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
    
}
