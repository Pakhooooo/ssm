package com.ssm.announcement.dto;

import com.ssm.common.global.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnnouncementListDTO extends BaseDTO {

    private String announcementTitle;
    
}
