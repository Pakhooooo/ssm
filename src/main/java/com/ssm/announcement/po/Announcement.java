package com.ssm.announcement.po;

import com.ssm.common.global.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "t_announcement")
@EqualsAndHashCode(callSuper = true)
public class Announcement extends BasePO {
    
    @Id
    private Integer id;

    @NotBlank(message = "公告标题不能为空")
    private String announcementTitle;

    @NotBlank(message = "公告内容不能为空")
    private String announcementContent;
    
    @NotNull(message = "发布人ID不能为空")
    private Integer publisherId;
    
}
