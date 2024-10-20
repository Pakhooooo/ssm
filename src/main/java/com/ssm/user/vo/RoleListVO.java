package com.ssm.user.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleListVO {

    private Integer id;
    
    private String roleCode;
    
    private String roleName;
    
    private List<UserPermissionVO> permissions;

}
