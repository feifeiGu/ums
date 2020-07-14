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
 * 角色表(OitRole)实体类
 *
 * @author guff
 * @since 2020-06-04 14:06:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "角色实体")
public class OitRole implements Serializable {
    private static final long serialVersionUID = -93894525906230933L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "角色ID")
    private Integer id;
    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**
    * 角色类型
    */
    @ApiModelProperty(value = "角色类型")
    @NotBlank(message = "角色类型不能为空")
    private String roleType;
    /**
    * 公司ID
    */
    @ApiModelProperty(value = "公司ID")
    @NotBlank(message = "公司ID不能为空")
    private Integer companyId;
    /**
    * 公司
    */
    @ApiModelProperty(value = "公司")
    @NotBlank(message = "公司不能为空")
    private String company;
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