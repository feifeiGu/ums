package com.oit.oitcloud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "组织架构传输对象")
public class OrganizeDTO implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父节点 树形结构
     */
    private Integer pid;
    /**
     * 根节点 树形结构
     */
    private Integer rid;
    /**
     * 关联用户数
     */
    private Integer releUserSize;
}
