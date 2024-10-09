package com.ssm.common.global;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BaseDTO {

    @NotNull(message = "pageNum 不能为空")
    @Min(value = 1, message = "pageNum 最小为1")
    protected Integer pageNum;

    @NotNull(message = "pageSize 不能为空")
    @Min(value = 0, message = "pageSize 最小为0")
    protected Integer pageSize;
    
}
