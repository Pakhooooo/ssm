package com.ssm.register.po;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_register")
@EqualsAndHashCode(callSuper = true)
public class Register extends BaseEntity {
    
    
    
}
