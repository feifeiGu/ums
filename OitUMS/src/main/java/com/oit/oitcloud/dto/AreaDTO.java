package com.oit.oitcloud.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "城市区域传输对象")
public class AreaDTO implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父节点 树形结构
     */
    private String pid;

    /**
     * 子节点
     */
    private List<AreaDTO> areaDTOList;
}
