package com.ssm.register.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_register")
@EqualsAndHashCode(callSuper = true)
public class Register extends BasePO {
    
    @Id
    private Integer id;
    
    private Integer userId;
    
    private Integer competitionId;
    
    private String registerStatus;
}
