package com.ssm.event.po;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_event")
@EqualsAndHashCode(callSuper = true)
public class Event extends BaseEntity {
    
    
    
}
