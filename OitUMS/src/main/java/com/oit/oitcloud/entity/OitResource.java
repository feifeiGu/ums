package com.oit.oitcloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
/**
 * 页面资源表(OitResource)实体类
 *
 * @author guff
 * @since 2020-06-05 13:21:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "资源实体")
public class OitResource implements Serializable {
    private static final long serialVersionUID = 910989711732469468L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "资源ID")
    private Integer id;
    /**
    * 应用ID
    */
    @ApiModelProperty(value = "应用ID")
    @NotBlank(message = "应用ID不能为空")
    private Integer appId;
    /**
    * 资源名称
    */
    @ApiModelProperty(value = "资源名称")
    @NotBlank(message = "资源名称不能为空")
    private String resourceName;
    /**
    * 资源编码
    */
    @ApiModelProperty(value = "资源编码")
    private String resourceCode;
    /**
    * 资源类型
    */
    @ApiModelProperty(value = "资源类型")
    @NotBlank(message = "资源类型不能为空")
    private String resourceType;
    /**
    * 资源路径
    */
    @ApiModelProperty(value = "资源路径")
    private String resourcePath;
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
    * 状态
    */
    @ApiModelProperty(value = "状态")
    private String status;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改人
    */
    private String modifyBy;
    /**
    * 修改时间
    */
    private Date modifyTime;


}