package com.oit.oitcloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询请求封装类
 */
@ApiModel(value = "分页请求")
@Data
public class PageRequest {
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码" ,example = "1")
    private int pageNum;
    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量" ,example = "50")
    private int pageSize;
}
