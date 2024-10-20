package com.ssm.user.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserListVO {

    private Integer userId;

    private String username;

    private Integer age;

    private String sex;

    private String realName;

    private String phone;

    private List<UserRoleVO> roles;

}
