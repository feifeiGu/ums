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
 * 系统应用表(OitApp)实体类
 *
 * @author guff
 * @since 2020-06-03 11:05:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "应用实体")
public class OitApp implements Serializable {
    private static final long serialVersionUID = -64248922953883435L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "应用ID")
    private Integer id;
    /**
    * 应用名称
    */
    @ApiModelProperty(value = "应用名称")
    @NotBlank(message = "应用名称不能为空")
    private String appName;
    /**
    * APP_KEY
    */
    @ApiModelProperty(value = "APP_KEY")
    @NotBlank(message = "APP_KEY不能为空")
    private String appKey;
    /**
    * APP_SECRET
    */
    @ApiModelProperty(value = "APP_SECRET")
    @NotBlank(message = "APP_SECRET不能为空")
    private String appSecret;
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