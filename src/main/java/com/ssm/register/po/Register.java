package com.ssm.register.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "t_competition_register")
@EqualsAndHashCode(callSuper = true)
public class Register extends BasePO {
    
    @Id
    private Integer id;

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    @NotNull(message = "赛事ID不能为空")
    private Integer competitionId;
    
    private String auditStatus;
}
