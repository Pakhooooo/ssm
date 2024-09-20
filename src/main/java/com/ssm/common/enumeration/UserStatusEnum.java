package com.ssm.common.enumeration;

public enum UserStatusEnum {

    AUDITING(0, "审核中"),

    APPROVED(1, "审核通过"),

    FAILED(-1, "审核不通过");

    private final int value;
    
    private final String desc;

    UserStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
