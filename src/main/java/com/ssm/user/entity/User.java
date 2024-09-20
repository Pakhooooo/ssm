package com.ssm.user.entity;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    
    @NotBlank(message = "用户名不能为空")
    private String userName;
    
    @NotBlank(message = "用户密码不能为空")
    private String password;
    
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    
    @NotBlank(message = "手机号码不能为空")
    private String phoneNumber;
    
}
