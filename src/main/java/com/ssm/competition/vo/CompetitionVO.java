package com.ssm.competition.vo;

import lombok.Data;

@Data
public class CompetitionVO {
    
    private Integer id;

    private String competitionName;

    private String competitionDate;

    private String competitionLocation;

    private Integer competitionPersonNumber;

    private String competitionDescription;
    
}
