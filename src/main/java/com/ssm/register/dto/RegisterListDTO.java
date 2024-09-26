package com.ssm.register.dto;

import com.ssm.common.global.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterListDTO extends BaseDTO {

    private Integer id;

    private String realName;

    private String competitionName;

    private String registerStatus;
    
}
