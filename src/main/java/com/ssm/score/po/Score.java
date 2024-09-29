package com.ssm.score.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "t_competition_score")
@EqualsAndHashCode(callSuper = true)
public class Score extends BasePO {
    
    @Id
    private Integer id;
    
    private Integer userId;
    
    private Integer competitionId;
    
    private BigDecimal competitionScore;
    
    private Integer competitionRank;
    
}
