package com.ssm.user.dto;

import com.ssm.common.global.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserListDTO extends BaseDTO {
    
    private String sex;
    
    private String realName;
}
