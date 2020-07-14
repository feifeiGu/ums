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
 * 角色资源表(OitRoleResource)实体类
 *
 * @author guff
 * @since 2020-06-04 16:06:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "角色资源")
public class OitRoleResource implements Serializable {
    private static final long serialVersionUID = -19029039510396301L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 角色ID
    */
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    /**
    * 资源权限
    */
    @ApiModelProperty(value = "资源码")
    private String resourceAuth;
    /**
    * 资源类型 1：菜单权限；2：按钮权限；
    */
    @ApiModelProperty(value = "资源类型 1：菜单权限；2：按钮权限；")
    private String resourceType;
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