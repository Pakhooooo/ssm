package com.ssm.common.global;

import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
public class BaseEntity implements Serializable {

    protected Date createdTime;

    protected Date updatedTime;

    public Date getCreatedTime() {
        return new Date();
    }

    public Date getUpdatedTime() {
        return new Date();
    }
}
