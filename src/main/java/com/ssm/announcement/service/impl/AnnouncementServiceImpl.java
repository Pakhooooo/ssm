package com.ssm.announcement.service.impl;

import com.ssm.announcement.mapper.AnnouncementMapper;
import com.ssm.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    
    private AnnouncementMapper announcementMapper;

    @Autowired
    public void setAnnouncementMapper(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }
}
