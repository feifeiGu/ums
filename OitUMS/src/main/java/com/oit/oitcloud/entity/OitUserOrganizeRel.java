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
 * 用户组织关系表(OitUserOrganizeRel)实体类
 *
 * @author guff
 * @since 2020-06-05 14:45:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "用户组织关系")
public class OitUserOrganizeRel implements Serializable {
    private static final long serialVersionUID = 389946148426699707L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 用户ID
    */
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    /**
    * 组织ID
    */
    @ApiModelProperty(value = "组织ID")
    private Integer organizeId;
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