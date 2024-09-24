package com.ssm.score.po;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_score")
@EqualsAndHashCode(callSuper = true)
public class Score extends BaseEntity {
    
    
    
}
