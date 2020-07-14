package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.dao.OitRoleResourceDao;
import com.oit.oitcloud.entity.OitRoleResource;
import com.oit.oitcloud.service.OitRoleResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色资源表(OitRoleResource)表服务实现类
 *
 * @author guff
 * @since 2020-06-04 16:06:17
 */
@Service("oitRoleResourceService")
public class OitRoleResourceServiceImpl implements OitRoleResourceService {
    @Resource
    private OitRoleResourceDao oitRoleResourceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitRoleResource queryById(Integer id) {
        return this.oitRoleResourceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitRoleResource> queryAllByLimit(int offset, int limit) {
        return this.oitRoleResourceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitRoleResource 实例对象
     * @return 实例对象
     */
    @Override
    public OitRoleResource insert(OitRoleResource oitRoleResource) {
        this.oitRoleResourceDao.insert(oitRoleResource);
        return oitRoleResource;
    }

    /**
     * 修改数据
     *
     * @param oitRoleResource 实例对象
     * @return 实例对象
     */
    @Override
    public OitRoleResource update(OitRoleResource oitRoleResource) {
        this.oitRoleResourceDao.update(oitRoleResource);
        return this.queryById(oitRoleResource.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitRoleResourceDao.deleteById(id) > 0;
    }
    
    /**
     * 通过实体作为筛选条件查询
     * @param oitRoleResource
     * @return
     */
    @Override
    public List<OitRoleResource> queryAll(OitRoleResource oitRoleResource) {
        return this.oitRoleResourceDao.queryAll(oitRoleResource);
    }
}