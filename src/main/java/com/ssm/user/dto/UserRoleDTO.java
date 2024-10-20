package com.ssm.user.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserRoleDTO {
    
    private Integer userId;
    
    private Set<Integer> roleIds;
    
}
