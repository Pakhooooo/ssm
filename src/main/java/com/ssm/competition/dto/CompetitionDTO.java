package com.ssm.competition.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CompetitionDTO {
    
    private Integer id;
    
    private String competitionName;
    
    private Date competitionDate;
    
    private String competitionLocation;
    
    private Integer competitionPersonNumber;

    private String competitionDescription;
    
}
