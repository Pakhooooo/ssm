package com.ssm.user.entity;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {

    private Integer id;
    
    @NotBlank(message = "用户名不能为空")
    private String userName;
    
    @NotBlank(message = "用户密码不能为空")
    private String password;
    
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    
    @NotBlank(message = "手机号码不能为空")
    private String phoneNumber;

    public User() {
        super.createTime = new Date();
        super.updateTime = new Date();
    }

    public User(Integer id, Integer delStatus) {
        this.id = id;
        super.delStatus = delStatus;
    }
}
