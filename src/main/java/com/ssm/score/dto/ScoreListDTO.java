package com.ssm.score.dto;

import com.ssm.common.global.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScoreListDTO extends BaseDTO {
    
    private String name;
    
    private Integer competitionId;
    
    private String competitionName;
    
}
