package com.ssm.score.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Table(name = "t_competition_score")
@EqualsAndHashCode(callSuper = true)
public class Score extends BasePO {
    
    @Id
    private Integer id;

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    @NotNull(message = "赛事ID不能为空")
    private Integer competitionId;
    
    @NotBlank(message = "比赛成绩不能为空")
    private BigDecimal competitionScore;

    @NotNull(message = "比赛排名不能为空")
    private Integer competitionRank;
    
}
