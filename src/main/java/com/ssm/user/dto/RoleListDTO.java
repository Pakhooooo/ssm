package com.ssm.user.dto;

import com.ssm.common.global.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListDTO extends BaseDTO {
    
    private String roleName;
    
}
