package com.oit.oitcloud.dto;

import com.oit.oitcloud.entity.OitRole;
import com.oit.oitcloud.entity.OitRoleResource;
import com.oit.oitcloud.entity.OitUserResource;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "角色资源传输对象")
public class RoleResourceDTO implements Serializable {
    //角色对象
    private OitRole oitRole;
    //角色的资源权限
    private List<OitRoleResource> oitRoleResources;
}
