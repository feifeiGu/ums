package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.oit.oitcloud.dto.RoleResourceDTO;
import com.oit.oitcloud.entity.OitRole;
import com.oit.oitcloud.entity.OitUserRoleRel;
import com.oit.oitcloud.entity.RestResponse;
import com.oit.oitcloud.service.OitRoleService;
import com.oit.oitcloud.service.OitUserRoleRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色表(OitRole)表控制层
 *
 * @author guff
 * @since 2020-06-04 14:06:43
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("oitRole")
public class OitRoleController {
    /**
     * 服务对象
     */
    @Resource
    private OitRoleService oitRoleService;
    @Resource
    private OitUserRoleRelService oitUserRoleRelService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiIgnore
    @GetMapping("roles/{id}")
    public OitRole getOitRole(@PathVariable("id") Integer id) {
        return this.oitRoleService.queryById(id);
    }

    /**
     * 新增角色及角色资源权限
     * @param roleResourceDTO
     * @return
     */
    @ApiOperation(value = "新增角色及角色资源权限", notes = "新增角色及角色资源权限", httpMethod = "POST", produces = "application/json")
    @PostMapping("roles")
    public RestResponse insert(@RequestBody(required = true) RoleResourceDTO roleResourceDTO){
        return oitRoleService.insertRoleResource(roleResourceDTO);
    }

    /**
     * 获取角色及角色资源权限
     * @param id
     * @return
     */
    @ApiOperation(value = "获取角色及角色资源权限", notes = "获取角色及角色资源权限", httpMethod = "GET", produces = "application/json")
    @GetMapping("roleResource")
    public RestResponse getById(@RequestParam(required = true) Integer id){
        try {
            return RestResponse.succuess(oitRoleService.queryRoleResource(id));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 修改角色及角色资源权限
     * @param id
     * @param roleResourceDTO
     * @return
     */
    @ApiOperation(value = "修改角色及角色资源权限", notes = "修改角色及角色资源权限", httpMethod = "PUT", produces = "application/json")
    @PutMapping("roles/{id}")
    public RestResponse update(@PathVariable("id") Integer id, @RequestBody(required = false) RoleResourceDTO roleResourceDTO) {
        return oitRoleService.updateRoleResource(id,roleResourceDTO);
    }

    /**
     * 角色列表
     * @param roleName
     * @return
     */
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("roles")
    public RestResponse getOitRoles(@RequestParam(required = false) String roleName, @RequestParam(required = false) String roleType, @RequestParam(required = false) Integer companyId) {
        try {
            OitRole oitRole = new OitRole();
            oitRole.setRoleName(roleName);
            oitRole.setRoleType(roleType);
            oitRole.setCompanyId(companyId);
            return RestResponse.succuess(oitRoleService.queryAll(oitRole));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 获取角色下的用户
     * @param roleId
     * @return
     */
    @ApiOperation(value = "获取角色下的用户ID", notes = "获取角色下的用户ID", httpMethod = "GET", produces = "application/json")
    @GetMapping("userRoleRel")
    public RestResponse queryRel(@RequestParam(required = true) String roleId) {
        try {
            OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
            oitUserRoleRel.setRoleId(Integer.valueOf(roleId));
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
    @ApiImplicitParam(name = "jsonData", value = "{\"userId\":\"1,2\",\"roleId\":\"1\"}")
    @PutMapping("userRoleRel")
    public RestResponse update(@RequestBody(required = true) String jsonData) {
        try {
            List<OitUserRoleRel> oitUserRoleRelList = new ArrayList<>();
            Map map = JSON.parseObject(jsonData);
            String userId = map.get("userId").toString();
            String roleId = map.get("roleId").toString();
            String[] userIds = userId.replace("[","").replace("]","").split(",");
            //支持给多用户授权多角色的场景,先统一删除关系,再统一授权关系
            OitUserRoleRel userRoleRel = new OitUserRoleRel();
            userRoleRel.setRoleId(Integer.valueOf(roleId));
            oitUserRoleRelService.deleteByCondition(userRoleRel);
            for(String uid : userIds){
                if(!uid.equals("")){
                    OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
                    oitUserRoleRel.setUserId(Integer.valueOf(uid));
                    oitUserRoleRel.setRoleId(Integer.valueOf(roleId));
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