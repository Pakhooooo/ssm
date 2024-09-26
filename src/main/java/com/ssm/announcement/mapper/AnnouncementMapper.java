package com.ssm.announcement.mapper;

import com.ssm.announcement.po.Announcement;
import com.ssm.competition.vo.CompetitionListVO;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {
    
    void deleteAnnouncementById(int announcementId);

    List<CompetitionListVO> getAnnouncements();
}
