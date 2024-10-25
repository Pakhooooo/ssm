package com.ssm.score.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreListVO {

    private Integer id;
    
    private Integer userId;
    
    private String realName;

    private String competitionName;

    private BigDecimal competitionScore;

    private Integer competitionRank;
    
    private String scoreStatus;
    
}
