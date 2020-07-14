package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.dao.OitResourceDao;
import com.oit.oitcloud.dto.ResourceDTO;
import com.oit.oitcloud.entity.OitResource;
import com.oit.oitcloud.entity.OitUser;
import com.oit.oitcloud.service.OitResourceService;
import com.oit.oitcloud.service.OitUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 页面资源表(OitResource)表服务实现类
 *
 * @author guff
 * @since 2020-06-05 13:21:08
 */
@Service("oitResourceService")
public class OitResourceServiceImpl implements OitResourceService {
    @Resource
    private OitResourceDao oitResourceDao;
    @Autowired
    private OitUserService oitUserService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitResource queryById(Integer id) {
        return this.oitResourceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitResource> queryAllByLimit(int offset, int limit) {
        return this.oitResourceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitResource 实例对象
     * @return 实例对象
     */
    @Override
    public OitResource insert(OitResource oitResource) {
        this.oitResourceDao.insert(oitResource);
        return oitResource;
    }

    /**
     * 修改数据
     *
     * @param oitResource 实例对象
     * @return 实例对象
     */
    @Override
    public OitResource update(OitResource oitResource) {
        this.oitResourceDao.update(oitResource);
        return this.queryById(oitResource.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitResourceDao.deleteById(id) > 0;
    }
    
    /**
     * 通过实体作为筛选条件查询
     * @param oitResource
     * @return
     */
    @Override
    public List<OitResource> queryAll(OitResource oitResource) {
        return this.oitResourceDao.queryAll(oitResource);
    }

    /**
     * 根据token获取用户菜单资源
     * @param authentication
     * @return
     */
    @Override
    public List<ResourceDTO> queryUserMenus(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authority = userDetails.getAuthorities().toString();
        OitUser oitUser = oitUserService.queryByUsername(userDetails.getUsername());
        OitResource oitResource = new OitResource();
        oitResource.setAppId(Integer.valueOf(oitUser.getUserType()));
        oitResource.setPid(0);
        List<OitResource> oitResourceList = oitResourceDao.queryAll(oitResource);
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for(OitResource resource : oitResourceList){
            if(authority.contains(resource.getResourceCode())){
                ResourceDTO resourceDTO = new ResourceDTO();
                resourceDTO.setId(resource.getId());
                resourceDTO.setName(resource.getResourceName());
                resourceDTO.setCode(resource.getResourceCode());
                resourceDTO.setPid(resource.getPid());
                resourceDTO.setPath(resource.getResourcePath());
                resourceDTO.setSort(resource.getSort());

                OitResource queryResource = new OitResource();
                queryResource.setPid(resource.getId());
                queryResource.setResourceType(OitUmsConstants.RESOURCE_MENU);
                List<OitResource> resourceList = oitResourceDao.queryAll(queryResource);
                List<ResourceDTO> childResourceDTOList = new ArrayList<>();
                for(OitResource childResource : resourceList){
                    if(authority.contains(childResource.getResourceCode())){
                        ResourceDTO childResourceDTO = new ResourceDTO();
                        childResourceDTO.setId(childResource.getId());
                        childResourceDTO.setName(childResource.getResourceName());
                        childResourceDTO.setCode(childResource.getResourceCode());
                        childResourceDTO.setPid(childResource.getPid());
                        childResourceDTO.setPath(childResource.getResourcePath());
                        childResourceDTO.setSort(childResource.getSort());
                        childResourceDTOList.add(childResourceDTO);
                    }
                }
                resourceDTO.setChildren(childResourceDTOList);
                resourceDTOList.add(resourceDTO);
            }
        }
        return resourceDTOList;
    }

    /**
     * 根据token和pid获取用户菜单按钮
     * @param authentication
     * @param pid
     * @return
     */
    @Override
    public List<ResourceDTO> queryUserMenuButtons(Authentication authentication, Integer pid) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authority = userDetails.getAuthorities().toString();
        OitResource queryResource = new OitResource();
        queryResource.setPid(pid);
        queryResource.setResourceType(OitUmsConstants.RESOURCE_BUTTON);
        List<OitResource> resourceList = oitResourceDao.queryAll(queryResource);
        List<ResourceDTO> resourceDTOList = new ArrayList<>();
        for(OitResource resource : resourceList){
            if(authority.contains(resource.getResourceCode())){
                ResourceDTO resourceDTO = new ResourceDTO();
                resourceDTO.setId(resource.getId());
                resourceDTO.setName(resource.getResourceName());
                resourceDTO.setCode(resource.getResourceCode());
                resourceDTO.setPid(resource.getPid());
                resourceDTO.setPath(resource.getResourcePath());
                resourceDTO.setSort(resource.getSort());
                resourceDTOList.add(resourceDTO);
            }
        }
        return resourceDTOList;
    }
}