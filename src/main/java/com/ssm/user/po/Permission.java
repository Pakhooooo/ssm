package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BasePO {

    @Id
    private Integer id;
    
    private String permissionName;
    
    private String permissionKey;
    
    private String permissionURL;
    
    private String method;
    
}
