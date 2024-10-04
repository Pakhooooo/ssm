package com.ssm.register.vo;

import lombok.Data;

@Data
public class RegisterVO {

    private Integer id;

    private Integer userId;

    private String registerName;

    private Integer competitionId;

    private String competitionName;

    private String auditStatus;
    
}
