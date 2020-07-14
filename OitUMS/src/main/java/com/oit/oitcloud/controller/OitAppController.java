package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.oit.oitcloud.dto.AppDTO;
import com.oit.oitcloud.entity.OitApp;
import com.oit.oitcloud.entity.RestResponse;
import com.oit.oitcloud.service.OitAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统应用表(OitApp)表控制层
 *
 * @author guff
 * @since 2020-06-03 11:05:39
 */
@Api(tags = "应用管理")
@RestController
@RequestMapping("oitApp")
public class OitAppController {
    /**
     * 服务对象
     */
    @Resource
    private OitAppService oitAppService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiIgnore
    @GetMapping("apps/{id}")
    public OitApp getOitApp(@PathVariable("id") Integer id) {
        return this.oitAppService.queryById(id);
    }

    /**
     * 注册app应用
     * @param jsonData
     * @return
     */
    @ApiIgnore
    @PostMapping("apps")
    public OitApp insert(@RequestBody(required = true) String jsonData){
        OitApp oitApp = JSON.parseObject(jsonData, OitApp.class);
        return this.oitAppService.insert(oitApp);
    }

    /**
     * 获取app应用列表
     * @return
     */
    @ApiOperation(value = "获取app应用列表", notes = "获取app应用列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("appList")
    public RestResponse getOitApps() {
        OitApp oitApp = new OitApp();
        return RestResponse.succuess(oitAppService.queryAll(oitApp));
    }
}