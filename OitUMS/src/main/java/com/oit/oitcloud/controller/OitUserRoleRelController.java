package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.oit.oitcloud.entity.OitUserRoleRel;
import com.oit.oitcloud.entity.RestResponse;
import com.oit.oitcloud.service.OitUserRoleRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户角色关系表(OitUserRoleRel)表控制层
 *
 * @author guff
 * @since 2020-06-04 15:13:18
 */
@Api(tags = "用户角色关系管理")
@RestController
@RequestMapping("oitUserRoleRel")
public class OitUserRoleRelController {
    /**
     * 服务对象
     */
    @Resource
    private OitUserRoleRelService oitUserRoleRelService;

    /**
     * 获取用户所有的角色/获取角色下的用户
     * @param userId
     * @param roleId
     * @return
     */
    @ApiOperation(value = "获取用户所有的角色/获取角色下的用户", notes = "获取用户所有的角色/获取角色下的用户", httpMethod = "GET", produces = "application/json")
    @GetMapping("userRoleRel")
    public RestResponse queryRel(@RequestParam(required = false) String userId, @RequestParam(required = false) String roleId) {
        try {
            OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
            if(userId != null){
                oitUserRoleRel.setUserId(Integer.valueOf(userId));
            }else if(roleId != null){
                oitUserRoleRel.setRoleId(Integer.valueOf(roleId));
            }
            return RestResponse.succuess(oitUserRoleRelService.queryAll(oitUserRoleRel));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 新增/修改用户角色关系
     * @param jsonData
     * @return
     */
    @ApiOperation(value = "新增/修改用户角色关系", notes = "新增/修改用户角色关系", httpMethod = "PUT", produces = "application/json")
    @ApiImplicitParam(name = "jsonData", value = "{\"userId\":\"1,2\",\"roleId\":\"1,2,3\"}")
    @PutMapping("userRoleRel")
    public RestResponse update(@RequestBody(required = true) String jsonData) {
        try {
            List<OitUserRoleRel> oitUserRoleRelList = new ArrayList<>();
            Map map = JSON.parseObject(jsonData);
            String userId = map.get("userId").toString();
            String roleId = map.get("roleId").toString();
            String[] userIds = userId.replace("[","").replace("]","").split(",");
            String[] roleIds = roleId.replace("[","").replace("]","").split(",");
            //支持给多用户授权多角色的场景,先统一删除关系,再统一授权关系
            for(String uid : userIds){
                OitUserRoleRel userRoleRel = new OitUserRoleRel();
                userRoleRel.setUserId(Integer.valueOf(uid));
                oitUserRoleRelService.deleteByCondition(userRoleRel);
                for(String rid : roleIds){
                    OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
                    oitUserRoleRel.setUserId(Integer.valueOf(uid));
                    oitUserRoleRel.setRoleId(Integer.valueOf(rid));
                    oitUserRoleRelService.insert(oitUserRoleRel);
                    oitUserRoleRelList.add(oitUserRoleRel);
                }
            }
            return RestResponse.succuess(oitUserRoleRelList);
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

}