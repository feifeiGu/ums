package com.oit.oitcloud.dto;

import com.oit.oitcloud.entity.OitUser;
import com.oit.oitcloud.entity.OitUserResource;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "用户资源传输对象")
public class UserResourceDTO implements Serializable {
    //用户对象
    private OitUser oitUser;
    //用户的资源权限
    private List<OitUserResource> oitUserResources;
}
