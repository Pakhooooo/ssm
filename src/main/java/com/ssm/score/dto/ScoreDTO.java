package com.ssm.score.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreDTO {

    private Integer id;

    private Integer userId;

    private Integer competitionId;

    private BigDecimal competitionScore;

    private Integer competitionRank;
    
    private String registerName;
    
}
