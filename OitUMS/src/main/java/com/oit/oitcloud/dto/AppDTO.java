package com.oit.oitcloud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "子系统对象")
public class AppDTO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "应用ID")
    private Integer id;
    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    private String appName;
}
