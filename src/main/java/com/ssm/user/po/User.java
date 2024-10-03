package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BasePO {

    private Integer id;
    
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @NotBlank(message = "用户密码不能为空")
    private String password;
    
    @Min(value = 16, message = "用户年龄不能小于16")
    @Max(value = 99, message = "用户年龄不能大于99")
    @NotNull(message = "用户年龄不能为空")
    private Integer age;
    
    @NotBlank(message = "用户性别不能为空")
    private String sex;
    
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    private List<Role> roles;
}
