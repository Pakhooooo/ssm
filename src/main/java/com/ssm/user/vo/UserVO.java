package com.ssm.user.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private Integer userId;
    
    private String userName;
    
    private Integer age;
    
    private String sex;
    
    private String realName;
    
    private String phoneNumber;

}
