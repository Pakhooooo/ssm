package com.ssm.common.global;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private Date createdTime;

    private Date updatedTime;

    public Date getCreatedTime() {
        return new Date(createdTime.getTime());
    }

    public Date getUpdatedTime() {
        return new Date(createdTime.getTime());
    }
}
