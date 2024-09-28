package com.ssm.competition.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_competition")
@EqualsAndHashCode(callSuper = true)
public class Competition extends BasePO {
    
    @Id
    private Integer id;
    
    private String competitionName;
    
    private String competitionDate;
    
    private String competitionLocation;
    
    private Integer competitionPersonNumber;
    
    private String competitionDescription;
    
}
