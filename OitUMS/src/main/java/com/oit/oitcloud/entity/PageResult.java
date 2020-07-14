package com.oit.oitcloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页查询结果封装类
 */
@ApiModel(value = "分页查询结果")
@Data
public class PageResult {
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
    /**
     * 记录总数
     */
    @ApiModelProperty(value = "记录总数" ,example = "100")
    private long totalSize;
    /**
     * 页码总数
     */
    @ApiModelProperty(value = "页码总数" ,example = "5")
    private int totalPages;
    /**
     * 数据模型
     */
    @ApiModelProperty(value = "数据模型" ,example = "[{}]")
    private List<?> content;
}
