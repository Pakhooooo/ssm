package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BasePO {
    
    @Id
    private Integer id;
    
    private Integer userId;
    
    private Integer roleId;
    
}
