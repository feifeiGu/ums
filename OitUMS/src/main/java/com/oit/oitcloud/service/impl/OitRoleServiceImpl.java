package com.oit.oitcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.dao.*;
import com.oit.oitcloud.dto.RoleDTO;
import com.oit.oitcloud.dto.RoleResourceDTO;
import com.oit.oitcloud.dto.UserResourceDTO;
import com.oit.oitcloud.entity.*;
import com.oit.oitcloud.enums.ResultCode;
import com.oit.oitcloud.service.OitRoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色表(OitRole)表服务实现类
 *
 * @author guff
 * @since 2020-06-04 14:06:43
 */
@Service("oitRoleService")
public class OitRoleServiceImpl implements OitRoleService {
    @Resource
    private OitRoleDao oitRoleDao;
    @Resource
    private OitRoleResourceDao oitRoleResourceDao;
    @Resource
    private OitAppDao oitAppDao;
    @Resource
    private OitUserRoleRelDao oitUserRoleRelDao;
    @Resource
    private OitUserDao oitUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitRole queryById(Integer id) {
        return this.oitRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitRole> queryAllByLimit(int offset, int limit) {
        return this.oitRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitRole 实例对象
     * @return 实例对象
     */
    @Override
    public OitRole insert(OitRole oitRole) {
        oitRole.setStatus(OitUmsConstants.ENABLE);
        this.oitRoleDao.insert(oitRole);
        return oitRole;
    }

    /**
     * 修改数据
     *
     * @param oitRole 实例对象
     * @return 实例对象
     */
    @Override
    public OitRole update(OitRole oitRole) {
        this.oitRoleDao.update(oitRole);
        return this.queryById(oitRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitRoleDao.deleteById(id) > 0;
    }

    /**
     * 角色列表
     * @param oitRole
     * @return
     */
    @Override
    public List<RoleDTO> queryAll(OitRole oitRole) {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        List<OitRole> oitRoleList = oitRoleDao.queryAll(oitRole);
        for(OitRole role : oitRoleList){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setOitRole(role);
            roleDTO.setId(role.getId());
            roleDTO.setRoleName(role.getRoleName());
            //获取应用服务名称
            if(role.getRoleType() != null){
                roleDTO.setRoleTypeName(oitAppDao.queryById(Integer.valueOf(role.getRoleType())).getAppName());
            }
            OitUserRoleRel oitUserRoleRel = new OitUserRoleRel();
            oitUserRoleRel.setRoleId(role.getId());
            //获取角色关联的用户数
            roleDTO.setReleUserSize(oitUserRoleRelDao.queryAll(oitUserRoleRel).size());
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    /**
     * 新增角色和角色资源
     * @param roleResourceDTO
     */
    @Override
    public RestResponse insertRoleResource(RoleResourceDTO roleResourceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        OitUser oitUser = oitUserDao.queryByUsername(userDetails.getUsername());
        OitRole oitRole = roleResourceDTO.getOitRole();
        OitRole role = new OitRole();
        role.setRoleName(oitRole.getRoleName());
        role.setCompanyId(oitUser.getCompanyId());
        if(oitRoleDao.queryByCondition(role).size()>0){
            return RestResponse.fail(ResultCode.ROLE_EXIST);
        }
        oitRole.setCompanyId(oitUser.getCompanyId());
        oitRole.setCompany(oitUser.getCompany());
        oitRole.setStatus(OitUmsConstants.ENABLE);
        oitRole.setCreateBy(userDetails.getUsername());
        oitRoleDao.insert(oitRole);
        if(roleResourceDTO.getOitRoleResources() != null) {
            List<OitRoleResource> oitRoleResourceList = roleResourceDTO.getOitRoleResources();
            for (OitRoleResource oitRoleResource : oitRoleResourceList) {
                oitRoleResource.setRoleId(oitRole.getId());
                oitRoleResource.setCreateBy(userDetails.getUsername());
                oitRoleResourceDao.insert(oitRoleResource);
            }
        }
        return RestResponse.succuess(oitRole);
    }

    /**
     * 修改角色和角色资源
     * @param roleId
     * @param roleResourceDTO
     */
    @Override
    public RestResponse updateRoleResource(Integer roleId, RoleResourceDTO roleResourceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        OitUser oitUser = oitUserDao.queryByUsername(userDetails.getUsername());
        OitRole oitRole = roleResourceDTO.getOitRole();
        OitRole originRole = oitRoleDao.queryById(roleId);
        if(!originRole.getRoleName().equals(oitRole.getRoleName())){
            OitRole role = new OitRole();
            role.setRoleName(oitRole.getRoleName());
            role.setCompanyId(oitUser.getCompanyId());
            if(oitRoleDao.queryByCondition(role).size()>0){
                return RestResponse.fail(ResultCode.ROLE_EXIST);
            }
        }
        oitRole.setId(roleId);
        oitRole.setModifyBy(userDetails.getUsername());
        oitRoleDao.update(oitRole);
        if(roleResourceDTO.getOitRoleResources() != null) {
            List<OitRoleResource> oitRoleResourceList = roleResourceDTO.getOitRoleResources();
            oitRoleResourceDao.deleteByRoleId(roleId);
            for (OitRoleResource oitRoleResource : oitRoleResourceList) {
                oitRoleResource.setRoleId(roleId);
                oitRoleResource.setCreateBy(userDetails.getUsername());
                oitRoleResourceDao.insert(oitRoleResource);
            }
        }
        return RestResponse.succuess(oitRole);
    }

    /**
     * 获取角色和角色资源
     * @param id
     * @return
     */
    @Override
    public RoleResourceDTO queryRoleResource(Integer id) {
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        OitRole oitRole = oitRoleDao.queryById(id);
        roleResourceDTO.setOitRole(oitRole);
        OitRoleResource oitRoleResource = new OitRoleResource();
        oitRoleResource.setRoleId(id);
        List<OitRoleResource> oitRoleResourceList = oitRoleResourceDao.queryAll(oitRoleResource);
        roleResourceDTO.setOitRoleResources(oitRoleResourceList);
        return roleResourceDTO;
    }
}