package com.ssm.user.entity;

import com.ssm.common.global.BaseEntity;
import com.ssm.user.vo.UserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private Integer id;
    
    @NotBlank(message = "用户名不能为空")
    private String userName;
    
    @NotBlank(message = "用户密码不能为空")
    private String password;
    
    @Min(value = 16)
    @Max(value = 99)
    @NotNull(message = "用户年龄不能为空")
    private Integer age;
    
    @NotBlank(message = "用户性别不能为空")
    private String sex;
    
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    
    @NotBlank(message = "手机号码不能为空")
    private String phoneNumber;

    public User() {
        
    }

    public User(Integer id, Integer delStatus) {
        this.id = id;
        super.delStatus = delStatus;
    }
    
    public UserVO getUserVO() {
        UserVO userVO = new UserVO();
        userVO.setUserId(this.getId());
        userVO.setUserName(this.getUserName());
        userVO.setAge(this.getAge());
        userVO.setSex(this.getSex());
        userVO.setRealName(this.getRealName());
        userVO.setPhoneNumber(this.getPhoneNumber());
        
        return userVO;
    }
}
