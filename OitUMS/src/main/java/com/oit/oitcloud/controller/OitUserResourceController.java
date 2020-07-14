package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.oit.oitcloud.entity.OitUserResource;
import com.oit.oitcloud.service.OitUserResourceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户资源表(OitUserResource)表控制层
 *
 * @author guff
 * @since 2020-06-04 11:47:17
 */
@Api(tags = "用户资源管理")
@RestController
@RequestMapping("oitUserResource")
public class OitUserResourceController {
    /**
     * 服务对象
     */
    @Resource
    private OitUserResourceService oitUserResourceService;

    /**
     * 通过userId获取resource资源
     * @param userId
     * @return
     */
    @ApiIgnore
    @GetMapping("resource")
    public List<OitUserResource> queryByUserId(@RequestParam(required = true) Integer userId) {
        OitUserResource oitUserResource = new OitUserResource();
        oitUserResource.setUserId(userId);
        return this.oitUserResourceService.queryAll(oitUserResource);
    }

    /**
     * 用户资源授权
     * @param jsonData
     * @return
     */
    @ApiIgnore
    @PostMapping("resource")
    public void insert(@RequestBody(required = true) String jsonData) {
        List<OitUserResource> oitUserResourceList = JSON.parseObject(jsonData, new TypeReference<List<OitUserResource>>(){});
        for(OitUserResource oitUserResource : oitUserResourceList){
            this.oitUserResourceService.insert(oitUserResource);
        }
    }

    /**
     * 修改用户权限
     * @param jsonData
     */
    @ApiIgnore
    @PutMapping("resource")
    public void update(@RequestBody(required = true) String jsonData) {
        List<OitUserResource> oitUserResourceList = JSON.parseObject(jsonData, new TypeReference<List<OitUserResource>>(){});
        for(OitUserResource oitUserResource : oitUserResourceList){
            OitUserResource originOitUserResource = oitUserResourceService.queryById(oitUserResource.getId());
            originOitUserResource.setResourceAuth(oitUserResource.getResourceAuth());
            this.oitUserResourceService.update(originOitUserResource);
        }
    }
}