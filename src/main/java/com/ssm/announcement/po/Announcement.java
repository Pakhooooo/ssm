package com.ssm.announcement.po;

import com.ssm.common.global.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@Table(name = "t_announcement")
@EqualsAndHashCode(callSuper = true)
public class Announcement extends BaseEntity {
    
    
    
}