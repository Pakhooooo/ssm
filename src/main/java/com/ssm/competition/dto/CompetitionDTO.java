package com.ssm.competition.dto;

import lombok.Data;

@Data
public class CompetitionDTO {
    
    private Integer id;
    
    private String competitionName;
    
    private String competitionDate;
    
    private String competitionLocation;
    
    private Integer competitionPersonNumber;

    private String competitionDescription;
    
}
