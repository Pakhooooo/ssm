package com.ssm.announcement.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_announcement")
@EqualsAndHashCode(callSuper = true)
public class Announcement extends BasePO {
    
    @Id
    private Integer id;
    
    private String announcementTitle;
    
    private String announcementContent;
    
    private Date publishTime;
    
    private Integer publisherId;
    
    private String publisherName;
    
}
