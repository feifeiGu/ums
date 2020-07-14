package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.oit.oitcloud.entity.OitRoleResource;
import com.oit.oitcloud.service.OitRoleResourceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色资源表(OitRoleResource)表控制层
 *
 * @author guff
 * @since 2020-06-04 16:06:17
 */
@Api(tags = "角色资源管理")
@RestController
@RequestMapping("oitRoleResource")
public class OitRoleResourceController {
    /**
     * 服务对象
     */
    @Resource
    private OitRoleResourceService oitRoleResourceService;

    /**
     * 通过roleId获取resource资源
     * @param roleId
     * @return
     */
    @ApiIgnore
    @GetMapping("resource")
    public List<OitRoleResource> queryByRoleId(@RequestParam(required = true) Integer roleId) {
        OitRoleResource oitRoleResource = new OitRoleResource();
        oitRoleResource.setRoleId(roleId);
        return this.oitRoleResourceService.queryAll(oitRoleResource);
    }

    /**
     * 角色资源授权
     * @param jsonData
     */
    @ApiIgnore
    @PostMapping("resource")
    public void insert(@RequestBody(required = true) String jsonData) {
        List<OitRoleResource> oitRoleResourceList = JSON.parseObject(jsonData, new TypeReference<List<OitRoleResource>>(){});
        for(OitRoleResource oitRoleResource : oitRoleResourceList){
            this.oitRoleResourceService.insert(oitRoleResource);
        }
    }

    /**
     * 修改角色权限
     * @param jsonData
     */
    @ApiIgnore
    @PutMapping("resource")
    public void update(@RequestBody(required = true) String jsonData) {
        List<OitRoleResource> oitRoleResourceList = JSON.parseObject(jsonData, new TypeReference<List<OitRoleResource>>(){});
        for(OitRoleResource oitRoleResource : oitRoleResourceList){
            OitRoleResource originOitRoleResource = oitRoleResourceService.queryById(oitRoleResource.getId());
            originOitRoleResource.setResourceAuth(oitRoleResource.getResourceAuth());
            this.oitRoleResourceService.update(originOitRoleResource);
        }
    }
}