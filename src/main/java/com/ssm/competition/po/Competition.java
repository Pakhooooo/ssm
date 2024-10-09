package com.ssm.competition.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "t_competition")
@EqualsAndHashCode(callSuper = true)
public class Competition extends BasePO {
    
    @Id
    private Integer id;
    
    @NotBlank(message = "比赛名称不能为空")
    private String competitionName;

    @NotBlank(message = "比赛日期不能为空")
    private String competitionDate;

    @NotBlank(message = "比赛地点不能为空")
    private String competitionLocation;

    @NotNull(message = "参赛人数不能为空")
    private Integer competitionPersonNumber;
    
    private String competitionDescription;
    
}
