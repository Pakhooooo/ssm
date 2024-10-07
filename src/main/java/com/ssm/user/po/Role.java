package com.ssm.user.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BasePO {

    @Id
    private Integer id;
    
    private String roleCode;
    
    private String roleName;

    private List<Permission> permissions;
    
}
