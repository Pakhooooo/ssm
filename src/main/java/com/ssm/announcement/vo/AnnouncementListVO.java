package com.ssm.announcement.vo;

import lombok.Data;

@Data
public class AnnouncementListVO {

    private Integer id;

    private String announcementTitle;

    private String announcementContent;

    private String publishTime;

    private String publisherName;
    
}
