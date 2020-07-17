package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.oit.oitcloud.dto.OrganizeDTO;
import com.oit.oitcloud.entity.OitOrganize;
import com.oit.oitcloud.entity.OitUserRoleRel;
import com.oit.oitcloud.entity.RestResponse;
import com.oit.oitcloud.service.OitOrganizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织结构表(OitOrganize)表控制层
 *
 * @author guff
 * @since 2020-06-05 14:28:59
 */
@Api(tags = "组织结构管理")
@RestController
@RequestMapping("oitOrganize")
public class OitOrganizeController {
    /**
     * 服务对象
     */
    @Resource
    private OitOrganizeService oitOrganizeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiIgnore
    @GetMapping("organize/{id}")
    public OitOrganize getOitOrganize(@PathVariable("id") Integer id) {
        return this.oitOrganizeService.queryById(id);
    }

    /**
     * 根据rid获取组织机构列表
     * @param rid
     * @return
     */
    @ApiOperation(value = "根据rid获取组织机构列表", notes = "根据rid获取组织机构列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("organize")
    public RestResponse getOitOrganizes(@RequestParam(required = false) Integer rid) {
        try {
            OitOrganize oitOrganize = new OitOrganize();
            oitOrganize.setRid(rid);
            return RestResponse.succuess(oitOrganizeService.queryAll(oitOrganize));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 新增组织机构
     * @param jsonData
     * @return
     */
    @ApiIgnore
    @PostMapping("organize")
    public OitOrganize insert(@RequestBody(required = true) String jsonData) {
        OitOrganize oitOrganize = JSON.parseObject(jsonData, OitOrganize.class);
        return this.oitOrganizeService.insert(oitOrganize);
    }

    /**
     * 修改组织机构
     * @param id
     * @param jsonData
     * @return
     */
    @ApiIgnore
    @PutMapping("organize/{id}")
    public OitOrganize update(@PathVariable("id") Integer id, @RequestBody(required = false) String jsonData) {
        OitOrganize oitOrganize = JSON.parseObject(jsonData, OitOrganize.class);
        oitOrganize.setId(id);
        return this.oitOrganizeService.update(oitOrganize);
    }

    /**
     * 删除组织机构
     * @param id
     * @return
     */
    @ApiIgnore
    @DeleteMapping("organize/{id}")
    public boolean delete(@PathVariable("id") Integer id) {
        return this.oitOrganizeService.deleteById(id);
    }

    /**
     * 同步钉钉的部门列表
     * @return
     */
    @ApiIgnore
    @GetMapping("organize/syncOrgan")
    public RestResponse syncOrgan() {
        return oitOrganizeService.syncOrgan();
    }
}