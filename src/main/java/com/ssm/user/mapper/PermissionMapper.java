package com.ssm.user.mapper;

import com.ssm.user.po.Permission;
import com.ssm.user.vo.PermissionListVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {

    List<PermissionListVO> getPermissions();
}
