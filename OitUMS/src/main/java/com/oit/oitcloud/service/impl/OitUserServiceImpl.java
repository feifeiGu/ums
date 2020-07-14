package com.oit.oitcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.dao.*;
import com.oit.oitcloud.dto.UserResourceDTO;
import com.oit.oitcloud.entity.*;
import com.oit.oitcloud.enums.AppType;
import com.oit.oitcloud.enums.ResultCode;
import com.oit.oitcloud.service.OitUserService;
import com.oit.oitcloud.util.PageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表(OitUser)表服务实现类
 *
 * @author guff
 * @since 2020-06-01 16:55:55
 */
@Service("oitUserService")
public class OitUserServiceImpl implements OitUserService {
    @Value("${dingtalk.appKey}")
    private String dingtalk_appKey;
    @Value("${dingtalk.appSecret}")
    private String dingtalk_appSecret;
    @Value("${dingtalk.getToken}")
    private String dingtalk_getToken;
    @Value("${dingtalk.getDepartmentUsers}")
    private String dingtalk_getDepartmentUsers;

    @Resource
    private CallRestServiceImpl callRestService;

    @Resource
    private OitUserDao oitUserDao;
    @Resource
    private OitUserResourceDao oitUserResourceDao;
    @Resource
    private OitOrganizeDao oitOrganizeDao;
    @Resource
    private OitUserOrganizeRelDao oitUserOrganizeRelDao;
    @Resource
    private OitUserRoleRelDao oitUserRoleRelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitUser queryById(Integer id) {
        return this.oitUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitUser> queryAllByLimit(int offset, int limit) {
        return this.oitUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitUser 实例对象
     * @return 实例对象
     */
    @Override
    public OitUser insert(OitUser oitUser) {
        //进行用户密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        oitUser.setPassword(passwordEncoder.encode(oitUser.getPassword().trim()));
        oitUser.setStatus(OitUmsConstants.ENABLE);
        this.oitUserDao.insert(oitUser);
        return oitUser;
    }

    /**
     * 修改数据
     *
     * @param oitUser 实例对象
     * @return 实例对象
     */
    @Override
    public OitUser update(OitUser oitUser) {
        this.oitUserDao.update(oitUser);
        return this.queryById(oitUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitUserDao.deleteById(id) > 0;
    }

    /**
     * 通过username删除数据
     * @param username
     * @return
     */
    @Override
    public boolean deleteByUsername(String username) {
        return this.oitUserDao.deleteByUsername(username) > 0;
    }

    /**
     * 通过username查询单条数据
     * @param username
     * @return
     */
    @Override
    public OitUser queryByUsername(String username) {
        return this.oitUserDao.queryByUsername(username);
    }

    /**
     * 通过实体作为筛选条件查询
     * @param oitUser
     * @return
     */
    @Override
    public List<OitUser> queryAll(OitUser oitUser) {
        return this.oitUserDao.queryAll(oitUser);
    }

    /**
     * 新增用户和用户资源
     * @param userResourceDTO
     */
    @Override
    public RestResponse insertUserResource(UserResourceDTO userResourceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        OitUser oitUser = userResourceDTO.getOitUser();
        OitUser user = oitUserDao.queryByUsername(oitUser.getUsername());
        if(user != null){
            return RestResponse.fail(ResultCode.USER_EXIST);
        }
        //进行用户密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        oitUser.setPassword(passwordEncoder.encode(oitUser.getPassword().trim()));
        oitUser.setStatus(OitUmsConstants.ENABLE);
        oitUser.setCreateBy(userDetails.getUsername());
        if(oitUser.getDepartmentId() != null){
            OitOrganize oitOrganize = oitOrganizeDao.queryById(oitUser.getDepartmentId());
            oitUser.setDepartment(oitOrganize.getName());
        }
        oitUserDao.insert(oitUser);
        //保存用户部门关系
        if(oitUser.getDepartmentId() != null){
            OitUserOrganizeRel oitUserOrganizeRel = new OitUserOrganizeRel();
            oitUserOrganizeRel.setUserId(oitUser.getId());
            oitUserOrganizeRel.setOrganizeId(oitUser.getDepartmentId());
            oitUserOrganizeRel.setCreateBy(userDetails.getUsername());
            oitUserOrganizeRelDao.insert(oitUserOrganizeRel);
        }
        List<OitUserResource> oitUserResourceList = userResourceDTO.getOitUserResources();
        for(OitUserResource oitUserResource : oitUserResourceList){
            if(oitUserResource.getResourceType().equals("2")){
                String[] roleIds = oitUserResource.getResourceAuth().replace("[","").replace("]","").split(",");
                //先删除用户角色关系
                OitUserRoleRel userRoleRel = new OitUserRoleRel();
                userRoleRel.setUserId(oitUser.getId());
                oitUserRoleRelDao.deleteByCondition(userRoleRel);
                for(String rid : roleIds){
                    if(!rid.equals("")){
                        OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
                        oitUserRoleRel.setUserId(oitUser.getId());
                        oitUserRoleRel.setRoleId(Integer.valueOf(rid));
                        oitUserRoleRel.setCreateBy(userDetails.getUsername());
                        oitUserRoleRelDao.insert(oitUserRoleRel);
                    }
                }
            }
            oitUserResource.setUserId(oitUser.getId());
            oitUserResource.setCreateBy(userDetails.getUsername());
            oitUserResourceDao.insert(oitUserResource);
        }
        return RestResponse.succuess(oitUser);
    }

    /**
     * 修改用户和用户资源
     * @param userId
     * @param userResourceDTO
     */
    @Override
    public RestResponse updateUserResource(Integer userId, UserResourceDTO userResourceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        OitUser oitUser = userResourceDTO.getOitUser();
        oitUser.setId(userId);
        if(oitUser.getPassword() != null){
            //进行用户密码加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            oitUser.setPassword(passwordEncoder.encode(oitUser.getPassword().trim()));
        }
        if(oitUser.getDepartmentId() != null){
            OitOrganize oitOrganize = oitOrganizeDao.queryById(oitUser.getDepartmentId());
            oitUser.setDepartment(oitOrganize.getName());
        }
        oitUser.setModifyBy(userDetails.getUsername());
        oitUserDao.update(oitUser);
        if(userResourceDTO.getOitUserResources() != null){
            //保存用户部门关系
            if(oitUser.getDepartmentId() != null){
                oitUserOrganizeRelDao.deleteByUserId(oitUser.getId());
                OitUserOrganizeRel oitUserOrganizeRel = new OitUserOrganizeRel();
                oitUserOrganizeRel.setUserId(oitUser.getId());
                oitUserOrganizeRel.setOrganizeId(oitUser.getDepartmentId());
                oitUserOrganizeRel.setCreateBy(userDetails.getUsername());
                oitUserOrganizeRelDao.insert(oitUserOrganizeRel);
            }
            List<OitUserResource> oitUserResourceList = userResourceDTO.getOitUserResources();
            oitUserResourceDao.deleteByUserId(userId);
            for(OitUserResource oitUserResource : oitUserResourceList){
                if(oitUserResource.getResourceType().equals("2")){
                    String[] roleIds = oitUserResource.getResourceAuth().replace("[","").replace("]","").split(",");
                    //先删除用户角色关系
                    OitUserRoleRel userRoleRel = new OitUserRoleRel();
                    userRoleRel.setUserId(oitUser.getId());
                    oitUserRoleRelDao.deleteByCondition(userRoleRel);
                    for(String rid : roleIds){
                        if(!rid.equals("")) {
                            OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
                            oitUserRoleRel.setUserId(oitUser.getId());
                            oitUserRoleRel.setRoleId(Integer.valueOf(rid));
                            oitUserRoleRel.setCreateBy(userDetails.getUsername());
                            oitUserRoleRelDao.insert(oitUserRoleRel);
                        }
                    }
                }
                oitUserResource.setUserId(userId);
                oitUserResource.setCreateBy(userDetails.getUsername());
                oitUserResourceDao.insert(oitUserResource);
            }
        }
        return RestResponse.succuess(oitUser);
    }

    /**
     * 分页查询接口
     * @param pageRequest 自定义，统一分页查询请求
     * @param oitUser 实体类条件参数
     * @return
     */
    @Override
    public PageResult findPage(PageRequest pageRequest, OitUser oitUser) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,oitUser));
    }

    /**
     * 同步钉钉的用户信息
     * @return
     */
    @Override
    public List<OitUser> syncUser() {
        List<OitUser> oitUserList = new ArrayList<>();
        //调用钉钉API获取token接口
        String tokenUrl = MessageFormat.format(dingtalk_getToken,dingtalk_appKey,dingtalk_appSecret);
        RestResponse response = callRestService.restTemplateCallGet(tokenUrl);
        if(response.getCode() == ResultCode.SUCCESS.getCode()){
            JSONObject jsonObj = JSON.parseObject(response.getData().toString());
            String accessToken = jsonObj.getString("access_token");
            OitOrganize oitOrganize = new OitOrganize();
            List<OitOrganize> oitOrganizeList = oitOrganizeDao.queryAll(oitOrganize);
            for(OitOrganize organize : oitOrganizeList){
                //调用钉钉API获取部门列表接口
                String departmentUserUrl = MessageFormat.format(dingtalk_getDepartmentUsers, accessToken, organize.getId());
                RestResponse userResponse = callRestService.restTemplateCallGet(departmentUserUrl);
                if(userResponse.getCode() == ResultCode.SUCCESS.getCode()){
                    JSONObject userJsonObj = JSON.parseObject(userResponse.getData().toString());
                    JSONArray jsonArray = (JSONArray)userJsonObj.get("userlist");
                    //用户信息及用户组织机构关系同步到mysql
                    oitUserOrganizeRelDao.deleteByOrganizeId(organize.getId());
                    for(int i=0;i<jsonArray.size();i++) {
                        //只同步有工号的员工信息
                        if(jsonArray.getJSONObject(i).get("jobnumber") != null){
                            OitUser oitUser = new OitUser();
                            oitUser.setUsername(jsonArray.getJSONObject(i).get("jobnumber").toString());
                            //进行用户密码加密
                            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                            oitUser.setPassword(passwordEncoder.encode(OitUmsConstants.DEFAULT_PASSWORD));
                            oitUser.setName(jsonArray.getJSONObject(i).get("name").toString());
                            if(jsonArray.getJSONObject(i).get("mobile") != null){
                                oitUser.setMobile(jsonArray.getJSONObject(i).get("mobile").toString());
                            }
                            if(jsonArray.getJSONObject(i).get("email") != null){
                                oitUser.setEmail(jsonArray.getJSONObject(i).get("email").toString());
                            }
                            if(jsonArray.getJSONObject(i).get("position") != null){
                                oitUser.setJobTitle(jsonArray.getJSONObject(i).get("position").toString());
                            }
                            oitUser.setJobId(jsonArray.getJSONObject(i).get("jobnumber").toString());
                            oitUser.setDepartmentId(organize.getId());
                            oitUser.setDepartment(organize.getName());
                            oitUser.setCompanyId(OitUmsConstants.COMPANY_ID);
                            oitUser.setCompany(OitUmsConstants.COMPANY_NAME);
                            oitUser.setUserType(AppType.PLATFORM.getCode().toString());
                            oitUser.setStatus(OitUmsConstants.ENABLE);
                            oitUser.setCreateBy(OitUmsConstants.CREATE_BY);
                            OitUser user = oitUserDao.queryByUsername(oitUser.getUsername());
                            if(user != null){
                                oitUserOrganizeRelDao.deleteByUserId(user.getId());
                            }
                            oitUserDao.deleteByUsername(oitUser.getUsername());
                            oitUserDao.insert(oitUser);
                            OitUserOrganizeRel oitUserOrganizeRel = new OitUserOrganizeRel();
                            oitUserOrganizeRel.setUserId(oitUser.getId());
                            oitUserOrganizeRel.setOrganizeId(organize.getId());
                            oitUserOrganizeRelDao.insert(oitUserOrganizeRel);
                            oitUserList.add(oitUser);
                        }
                    }
                }
            }
        }
        return oitUserList;
    }

    /**
     * 获取用户及用户资源
     * @param id
     * @return
     */
    @Override
    public UserResourceDTO queryUserResource(Integer id) {
        UserResourceDTO userResourceDTO = new UserResourceDTO();
        OitUser oitUser = oitUserDao.queryById(id);
        userResourceDTO.setOitUser(oitUser);
        OitUserResource oitUserResource = new OitUserResource();
        oitUserResource.setUserId(id);
        List<OitUserResource> oitUserResourceList = oitUserResourceDao.queryAll(oitUserResource);
        userResourceDTO.setOitUserResources(oitUserResourceList);
        return userResourceDTO;
    }

    /**
     * 根据部门ID获取用户列表分页接口
     * @param pageRequest
     * @param oitUser
     * @param departmentId
     * @return
     */
    @Override
    public PageResult findPageByDepartment(PageRequest pageRequest, OitUser oitUser, Integer departmentId) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("organizeId",departmentId);
        map.put("username",oitUser.getUsername());
        map.put("name",oitUser.getName());
        map.put("mobile",oitUser.getMobile());
        List<OitUser> oitUserList = oitUserDao.queryAllByDepartment(map);
        return PageUtils.getPageResult(pageRequest, new PageInfo<OitUser>(oitUserList));
    }

    private PageInfo<OitUser> getPageInfo(PageRequest pageRequest, OitUser oitUser) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<OitUser> oitUserList = oitUserDao.queryAll(oitUser);
        return new PageInfo<OitUser>(oitUserList);
    }
}