package com.ssm.user.entity;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {

    private Integer id;
    
    private String permissionName;
    
    private String permissionKey;
    
    private String permissionURL;
    
    private String method;
    
    private String description;

}
