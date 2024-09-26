package com.ssm.competition.dto;

import com.ssm.common.global.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompetitionListDTO extends BaseDTO {

    private String competitionName;

    private Date competitionDate;

    private String competitionLocation;
    
}
