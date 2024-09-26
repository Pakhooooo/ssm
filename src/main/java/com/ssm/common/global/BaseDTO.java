package com.ssm.common.global;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BaseDTO {

    @NotNull(message = "pageNum 不能为空")
    @Min(value = 1, message = "pageNum 最小为1")
    @Max(value = 99, message = "pageNum 最大为99")
    protected Integer pageNum;

    @NotNull(message = "pageSize 不能为空")
    @Min(value = 10, message = "pageSize 最小为10")
    @Max(value = 50, message = "pageSize 最大为50")
    protected Integer pageSize;
    
}
