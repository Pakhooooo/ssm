package com.ssm.user.entity;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {
    
    private Integer id;
    
    private Integer userId;
    
    private Integer roleId;
    
}
