package com.ssm.user.mapper;

import com.ssm.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    
    UserVO getUserInfoById(int userId);
}
