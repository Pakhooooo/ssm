package com.ssm.announcement.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AnnouncementVO {

    private Integer id;

    private String announcementTitle;

    private String announcementContent;

    private Date publishTime;

    private String publisherName;
    
}
