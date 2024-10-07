package com.ssm.user.mapper;

import com.ssm.user.po.Role;
import com.ssm.user.vo.RoleListVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    
    List<RoleListVO> getRoles();
    
}
