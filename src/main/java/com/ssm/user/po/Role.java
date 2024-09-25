package com.ssm.user.po;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private Integer id;
    
    private String roleName;

    private List<Permission> permissions;
    
}
