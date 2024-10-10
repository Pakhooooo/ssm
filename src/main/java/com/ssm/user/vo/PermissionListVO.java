package com.ssm.user.vo;

import lombok.Data;

@Data
public class PermissionListVO {
    
    private Integer id;
    
    private String permissionName;
    
    private String permissionKey;
    
    private String permissionURL;
    
    private String permissionType;
    
}
