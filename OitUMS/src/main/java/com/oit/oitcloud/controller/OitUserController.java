package com.oit.oitcloud.controller;

import com.alibaba.fastjson.JSON;
import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.dto.UserPageDTO;
import com.oit.oitcloud.dto.UserResourceDTO;
import com.oit.oitcloud.entity.*;
import com.oit.oitcloud.service.OitUserResourceService;
import com.oit.oitcloud.service.OitUserRoleRelService;
import com.oit.oitcloud.service.OitUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户表(OitUser)表控制层
 *
 * @author guff
 * @since 2020-06-01 16:55:55
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("oitUser")
public class OitUserController {

    /**
     * 服务对象
     */
    @Resource
    private OitUserService oitUserService;
    @Resource
    private OitUserRoleRelService oitUserRoleRelService;
    @Resource
    private OitUserResourceService oitUserResourceService;

    /**
     * 通过主键ID获取用户信息
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键ID获取用户信息", notes = "通过主键ID获取用户信息", httpMethod = "GET", produces = "application/json")
    @GetMapping("users/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public OitUser getOitUser(@PathVariable("id") Integer id) {
        return this.oitUserService.queryById(id);
    }

    /**
     * 通过username唯一键查询单条数据
     * @param username
     * @return
     */
    @ApiOperation(value = "获取用户数据", notes = "获取用户数据", httpMethod = "GET", produces = "application/json")
    @GetMapping("user")
    public RestResponse getByUsername(@RequestParam(required = true) String username) {
        try {
            return RestResponse.succuess(oitUserService.queryByUsername(username));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 获取用户列表
     * @param username
     * @param name
     * @param mobile
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("users")
    public RestResponse getOitUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String name, @RequestParam(required = false) String mobile, @RequestParam(required = false) Integer companyId) {
        try {
            OitUser oitUser = new OitUser();
            oitUser.setUsername(username);
            oitUser.setName(name);
            oitUser.setMobile(mobile);
            oitUser.setCompanyId(companyId);
            return RestResponse.succuess(this.oitUserService.queryAll(oitUser));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 获取用户列表分页接口
     * @param userPageDTO
     * @return
     */
    @ApiOperation(value = "获取用户列表分页接口", notes = "获取用户列表分页接口", httpMethod = "POST", produces = "application/json")
    @PostMapping(value="/findPage")
    public RestResponse findPage(@RequestBody(required = true) @Valid UserPageDTO userPageDTO) {
        try {
            PageResult pageResult;
            OitUser oitUser = new OitUser();
            oitUser.setUsername(userPageDTO.getUsername());
            oitUser.setName(userPageDTO.getName());
            oitUser.setMobile(userPageDTO.getMobile());
            PageRequest pageRequest = userPageDTO.getPageRequest();
            if(userPageDTO.getDepartmentId() != 0){
                pageResult = oitUserService.findPageByDepartment(pageRequest,oitUser,userPageDTO.getDepartmentId());
            }else{
                oitUser.setCompanyId(userPageDTO.getCompanyId());
                pageResult = oitUserService.findPage(pageRequest,oitUser);
            }
            return RestResponse.succuess(pageResult);
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 新增用户及用户资源权限
     * @param userResourceDTO
     * @return
     */
    @ApiOperation(value = "新增用户及用户资源权限", notes = "新增用户及用户资源权限", httpMethod = "POST", produces = "application/json")
    @PostMapping("users")
    public RestResponse insert(@RequestBody(required = true) @Valid UserResourceDTO userResourceDTO){
        return oitUserService.insertUserResource(userResourceDTO);
    }

    /**
     * 获取用户信息及用户资源权限
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户信息及用户资源权限", notes = "获取用户信息及用户资源权限", httpMethod = "GET", produces = "application/json")
    @GetMapping("userResource")
    public RestResponse getById(@RequestParam(required = true) Integer id){
        try {
            return RestResponse.succuess(oitUserService.queryUserResource(id));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 修改用户及用户资源权限
     * @param id
     * @param userResourceDTO
     * @return
     */
    @ApiOperation(value = "修改用户及用户资源权限", notes = "修改用户及用户资源权限", httpMethod = "PUT", produces = "application/json")
    @PutMapping("users/{id}")
    public RestResponse update(@PathVariable("id") Integer id, @RequestBody(required = false) UserResourceDTO userResourceDTO) {
        return oitUserService.updateUserResource(id, userResourceDTO);
    }

    /**
     * 用户重置密码
     * @param id
     * @return
     */
    @ApiOperation(value = "重置用户密码", notes = "用户重置密码", httpMethod = "PUT", produces = "application/json")
    @PutMapping("users/resetPassword/{id}")
    public RestResponse resetPassword(@PathVariable("id") Integer id) {
        try {
            OitUser oitUser = oitUserService.queryById(id);
            //进行用户密码加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            oitUser.setPassword(passwordEncoder.encode(OitUmsConstants.DEFAULT_PASSWORD));
            return RestResponse.succuess(oitUserService.update(oitUser));
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 用户禁用
     * @param id
     * @return
     */
    @ApiIgnore
    @DeleteMapping("users/{id}")
    public OitUser delete(@PathVariable("id") Integer id) {
        OitUser oitUser = oitUserService.queryById(id);
        oitUser.setStatus(OitUmsConstants.DISABLE);
        return this.oitUserService.update(oitUser);
    }

    /**
     * 获取用户所有的角色
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户所有的角色ID", notes = "获取用户所有的角色ID", httpMethod = "GET", produces = "application/json")
    @GetMapping("userRoleRel")
    public RestResponse queryRel(@RequestParam(required = true) String userId) {
        try {
            OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
            oitUserRoleRel.setUserId(Integer.valueOf(userId));
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
    @ApiImplicitParam(name = "jsonData", value = "{\"userId\":\"1\",\"roleId\":\"1,2,3\"}")
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

    /**
     * 同步钉钉的用户信息
     * @return
     */
    @ApiIgnore
    @GetMapping("users/syncUser")
    public RestResponse syncUser() {
        try {
            return RestResponse.succuess(oitUserService.syncUser());
        } catch (Exception e) {
            return RestResponse.fail();
        }
    }

    /**
     * 根据token获取用户信息
     * @return
     */
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息", httpMethod = "GET", produces = "application/json")
    @GetMapping("/users/info")
    public RestResponse userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        OitUser oitUser = oitUserService.queryByUsername(userDetails.getUsername());
        return RestResponse.succuess(oitUser);
    }

    /**
     * 根据token获取用户信息及权限数据
     * @return
     */
    @ApiOperation(value = "根据token获取用户信息及权限数据", notes = "根据token获取用户信息及权限数据", httpMethod = "GET", produces = "application/json")
    @GetMapping("/users/authors")
    public RestResponse userAuthorities() {
        UserResourceDTO userResourceDTO = new UserResourceDTO();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        OitUser oitUser = oitUserService.queryByUsername(userDetails.getUsername());
        userResourceDTO.setOitUser(oitUser);
        OitUserResource oitUserResource = new OitUserResource();
        oitUserResource.setUserId(oitUser.getId());
        List<OitUserResource> oitUserResourceList = oitUserResourceService.queryAll(oitUserResource);
        userResourceDTO.setOitUserResources(oitUserResourceList);
        return RestResponse.succuess(userResourceDTO);
    }
}