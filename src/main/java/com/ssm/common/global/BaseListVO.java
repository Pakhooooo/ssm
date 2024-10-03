package com.ssm.common.global;

import lombok.Data;

import java.util.List;

@Data
public class BaseListVO<T> {
    
    private Long total;

    private List<T> page;
    
}
