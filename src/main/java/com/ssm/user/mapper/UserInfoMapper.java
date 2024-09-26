package com.ssm.user.mapper;

import com.ssm.user.po.User;
import com.ssm.user.vo.UserListVO;
import com.ssm.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    
    UserVO getUserInfoById(int userId);

    List<UserListVO> getUserList();
    
    void deleteUserInfoById(int userId);
    
    void updateUserInfo(@Param("user") User user);
    
    User getUserById(int userId);
}
