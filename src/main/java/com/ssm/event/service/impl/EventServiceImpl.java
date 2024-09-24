package com.ssm.event.service.impl;

import com.ssm.event.mapper.EventMapper;
import com.ssm.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    
    private EventMapper eventMapper;

    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }
}
