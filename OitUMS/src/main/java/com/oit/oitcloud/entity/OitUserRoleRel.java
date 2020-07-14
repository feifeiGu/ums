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
 * 用户角色关系表(OitUserRoleRel)实体类
 *
 * @author guff
 * @since 2020-06-04 15:13:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "用户角色关系")
public class OitUserRoleRel implements Serializable {
    private static final long serialVersionUID = 563738102092735988L;
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
    * 角色ID
    */
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
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