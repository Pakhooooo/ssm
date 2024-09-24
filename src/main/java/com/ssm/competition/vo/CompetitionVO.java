package com.ssm.competition.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CompetitionVO {
    
    private Integer id;

    private String competitionName;

    private Date competitionDate;

    private String competitionLocation;

    private Integer competitionPersonNumber;

    private String competitionDescription;
    
}
