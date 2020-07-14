package com.oit.oitcloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(OitUser)实体类
 *
 * @author guff
 * @since 2020-06-02 14:37:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "用户实体")
public class OitUser implements Serializable {
    private static final long serialVersionUID = 419104868695158242L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "用户ID")
    private Integer id;
    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
    * 密码
    */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message="密码必须是6-16位的数字与字母组合,不能纯数字或纯英文")
    private String password;
    /**
    * 姓名
    */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
    * 手机号
    */
    @ApiModelProperty(value = "手机号")
    @Pattern(regexp="^((1))\\d{10}$", message="请输入正确的手机号")
    private String mobile;
    /**
    * 电子邮箱
    */
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    /**
    * 工号
    */
    @ApiModelProperty(value = "工号")
    private String jobId;
    /**
    * 职位
    */
    @ApiModelProperty(value = "职位")
    private String jobTitle;
    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Integer departmentId;
    /**
    * 部门
    */
    @ApiModelProperty(value = "部门")
    private String department;
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
    * 用户类型
    */
    @ApiModelProperty(value = "用户类型")
    @NotBlank(message = "用户类型不能为空")
    private String userType;
    /**
    * 状态 1：启用；0：禁用
    */
    @ApiModelProperty(value = "状态 1：启用；0：禁用")
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