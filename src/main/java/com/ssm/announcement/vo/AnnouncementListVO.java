package com.ssm.announcement.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AnnouncementListVO {

    private Integer id;

    private String announcementTitle;

    private String announcementContent;

    private Date publishTime;

    private String publisherName;
    
}
