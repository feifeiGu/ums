package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.entity.OitResource;
import com.oit.oitcloud.entity.OitUserRoleRel;
import com.oit.oitcloud.entity.RestResponse;
import com.oit.oitcloud.service.OitResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 页面资源表(OitResource)表控制层
 *
 * @author guff
 * @since 2020-06-05 13:21:08
 */
@Api(tags = "页面资源管理")
@RestController
@RequestMapping("oitResource")
public class OitResourceController {
    /**
     * 服务对象
     */
    @Resource
    private OitResourceService oitResourceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiIgnore
    @GetMapping("menu/{id}")
    public OitResource getOitMenu(@PathVariable("id") Integer id) {
        return this.oitResourceService.queryById(id);
    }

    /**
     * 通过AppId获取菜单资源列表
     * @param appId
     * @return
     */
    @ApiOperation(value = "通过AppId获取菜单资源列表", notes = "通过AppId获取菜单资源列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("menu")
    public RestResponse getOitMenusByApp(@RequestParam(required = true) Integer appId) {
        try {
            OitResource oitResource = new OitResource();
            oitResource.setAppId(appId);
            oitResource.setPid(0);
            return RestResponse.succuess(oitResourceService.queryAll(oitResource));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 通过pid获取二级菜单列表
     * @param pid
     * @return
     */
    @ApiOperation(value = "通过pid获取二级菜单列表", notes = "通过pid获取二级菜单列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("menuByPid")
    public RestResponse getOitMenusByPid(@RequestParam(required = true) Integer pid) {
        try {
            OitResource oitResource = new OitResource();
            oitResource.setPid(pid);
            oitResource.setResourceType(OitUmsConstants.RESOURCE_MENU);
            return RestResponse.succuess(oitResourceService.queryAll(oitResource));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 通过pid获取三级按钮资源列表
     * @param pid
     * @return
     */
    @ApiOperation(value = "通过pid获取三级按钮资源列表", notes = "通过pid获取三级按钮资源列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("buttonByPid")
    public RestResponse getOitButtonsByPid(@RequestParam(required = true) Integer pid) {
        try {
            OitResource oitResource = new OitResource();
            oitResource.setPid(pid);
            oitResource.setResourceType(OitUmsConstants.RESOURCE_BUTTON);
            return RestResponse.succuess(oitResourceService.queryAll(oitResource));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 新增菜单资源
     * @param jsonData
     * @return
     */
    @ApiIgnore
    @PostMapping("menu")
    public OitResource insert(@RequestBody(required = true) String jsonData) {
        OitResource oitResource = JSON.parseObject(jsonData, OitResource.class);
        return this.oitResourceService.insert(oitResource);
    }

    /**
     * 修改菜单资源
     * @param id
     * @param jsonData
     * @return
     */
    @ApiIgnore
    @PutMapping("menu/{id}")
    public OitResource update(@PathVariable("id") Integer id, @RequestBody(required = false) String jsonData) {
        OitResource oitResource = JSON.parseObject(jsonData, OitResource.class);
        oitResource.setId(id);
        return this.oitResourceService.update(oitResource);
    }

    /**
     * 逻辑删除菜单资源
     * @param id
     * @return
     */
    @ApiIgnore
    @DeleteMapping("menu/{id}")
    public OitResource delete(@PathVariable("id") Integer id) {
        OitResource oitResource = oitResourceService.queryById(id);
        oitResource.setStatus(OitUmsConstants.DISABLE);
        return this.oitResourceService.update(oitResource);
    }

    /**
     * 根据token获取用户菜单
     * @return
     */
    @ApiOperation(value = "根据token获取用户菜单", notes = "根据token获取用户菜单", httpMethod = "GET", produces = "application/json")
    @GetMapping("/userMenus")
    public RestResponse userMenus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return RestResponse.succuess(oitResourceService.queryUserMenus(authentication));
    }

    /**
     * 根据token和pid获取用户菜单按钮
     * @param pid
     * @return
     */
    @ApiOperation(value = "根据token获取用户菜单按钮", notes = "根据token获取用户菜单按钮", httpMethod = "GET", produces = "application/json")
    @GetMapping("/userMenuButtons")
    public RestResponse userMenuButtons(@RequestParam(required = true) Integer pid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return RestResponse.succuess(oitResourceService.queryUserMenuButtons(authentication,pid));
    }
}