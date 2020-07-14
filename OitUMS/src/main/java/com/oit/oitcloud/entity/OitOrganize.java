package com.oit.oitcloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
/**
 * 组织结构表(OitOrganize)实体类
 *
 * @author guff
 * @since 2020-06-05 14:28:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "组织机构实体")
public class OitOrganize implements Serializable {
    private static final long serialVersionUID = 466656603042813469L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "组织ID")
    private Integer id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
    * 父节点 树形结构
    */
    @ApiModelProperty(value = "父节点")
    private Integer pid;
    /**
    * 根节点 树形结构
    */
    @ApiModelProperty(value = "根节点")
    private Integer rid;
    /**
    * 排序
    */
    @ApiModelProperty(value = "排序")
    private String sort;
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