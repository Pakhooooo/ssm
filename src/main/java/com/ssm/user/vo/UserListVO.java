package com.ssm.user.vo;

import com.ssm.common.global.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserListVO extends BaseVO {

    private Integer userId;

    private String userName;

    private Integer age;

    private String sex;

    private String realName;

    private String phoneNumber;

}
