package com.oit.oitcloud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "页面资源传输对象")
public class ResourceDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "资源ID")
    private Integer id;
    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    @NotBlank(message = "资源名称不能为空")
    private String name;
    /**
     * 资源编码
     */
    @ApiModelProperty(value = "资源编码")
    private String code;
    /**
     * 资源路径
     */
    @ApiModelProperty(value = "资源路径")
    private String path;
    /**
     * 父节点
     */
    @ApiModelProperty(value = "父节点")
    private Integer pid;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private String sort;
    /**
     * 子节点
     */
    private List<ResourceDTO> children;
}
