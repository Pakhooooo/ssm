package com.ssm.score.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreListVO {

    private Integer id;

    private String name;

    private String competitionName;

    private BigDecimal score;

    private Integer rank;
    
}
