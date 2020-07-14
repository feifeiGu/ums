package com.oit.oitcloud.dto;

import com.oit.oitcloud.entity.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户列表分页")
public class UserPageDTO implements Serializable {
    //用户名
    @ApiModelProperty(value = "用户名" ,example = "abc")
    private String username;
    //姓名
    @ApiModelProperty(value = "姓名" ,example = "abc")
    private String name;
    //手机号
    @ApiModelProperty(value = "手机号" ,example = "13912345678")
    private String mobile;
    //部门ID
    @ApiModelProperty(value = "部门ID" ,example = "12")
    private Integer departmentId;
    //公司ID
    @ApiModelProperty(value = "公司ID" ,example = "1")
    private Integer companyId;
    //分页请求
    @ApiModelProperty(value = "分页请求" ,example = "{}")
    private PageRequest pageRequest;
}
