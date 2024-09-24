package com.ssm.user.po;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private Long id;
    
    private String roleName;
    
}
