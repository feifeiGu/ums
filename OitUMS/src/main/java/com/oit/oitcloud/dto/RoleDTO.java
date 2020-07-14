package com.oit.oitcloud.dto;

import com.oit.oitcloud.entity.OitRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "角色传输对象")
public class RoleDTO implements Serializable {
    //角色对象
    private OitRole oitRole;
    //角色主键
    @ApiModelProperty(value = "角色主键")
    private Integer id;
    //角色名称
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    //角色类型名称
    @ApiModelProperty(value = "角色类型名称")
    private String roleTypeName;
    //关联用户数
    @ApiModelProperty(value = "关联用户数")
    private Integer releUserSize;
}
