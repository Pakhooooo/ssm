package com.ssm.user.vo;

import com.ssm.user.entity.UserRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserVO implements Serializable {

    private Integer userId;
    
    private String userName;
    
    private Integer age;
    
    private String sex;
    
    private String realName;
    
    private String phoneNumber;
    
    private List<UserRole> roles;

}
