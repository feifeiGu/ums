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
 * 用户资源表(OitUserResource)实体类
 *
 * @author guff
 * @since 2020-06-04 11:47:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "用户资源")
public class OitUserResource implements Serializable {
    private static final long serialVersionUID = -15889484617042841L;
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
    * 资源权限
    */
    @ApiModelProperty(value = "资源码")
    private String resourceAuth;
    /**
    * 资源类型 1：应用权限(1平台,2三方,3服务商,4承运商,5TMS)；2：角色权限；3：区域权限；4：客户；5：服务商；6：承运商
    */
    @ApiModelProperty(value = "资源类型 1：应用权限(1平台,2三方,3服务商,4承运商,5TMS)；2：角色权限；3：区域权限；4：客户；5：服务商；6：承运商")
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