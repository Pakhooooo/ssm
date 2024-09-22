package com.ssm.user.mapper;

import com.ssm.user.entity.User;
import com.ssm.user.vo.UserVO;
import tk.mybatis.mapper.common.Mapper;

public interface UserInfoMapper extends Mapper<User> {

    UserVO getUserByUserName(String userName);
    
}
