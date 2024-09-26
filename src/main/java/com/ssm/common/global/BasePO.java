package com.ssm.common.global;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePO implements Serializable {

    protected Date createTime;

    protected Date updateTime;

    protected Integer delStatus;
}
