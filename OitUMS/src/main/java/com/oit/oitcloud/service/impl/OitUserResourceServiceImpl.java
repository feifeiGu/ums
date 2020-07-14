package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.dao.OitUserResourceDao;
import com.oit.oitcloud.entity.OitUserResource;
import com.oit.oitcloud.service.OitUserResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户资源表(OitUserResource)表服务实现类
 *
 * @author guff
 * @since 2020-06-04 11:47:17
 */
@Service("oitUserResourceService")
public class OitUserResourceServiceImpl implements OitUserResourceService {
    @Resource
    private OitUserResourceDao oitUserResourceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitUserResource queryById(Integer id) {
        return this.oitUserResourceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitUserResource> queryAllByLimit(int offset, int limit) {
        return this.oitUserResourceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitUserResource 实例对象
     * @return 实例对象
     */
    @Override
    public OitUserResource insert(OitUserResource oitUserResource) {
        this.oitUserResourceDao.insert(oitUserResource);
        return oitUserResource;
    }

    /**
     * 修改数据
     *
     * @param oitUserResource 实例对象
     * @return 实例对象
     */
    @Override
    public OitUserResource update(OitUserResource oitUserResource) {
        this.oitUserResourceDao.update(oitUserResource);
        return this.queryById(oitUserResource.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitUserResourceDao.deleteById(id) > 0;
    }

    /**
     * 通过userId获取resource资源
     * @param oitUserResource
     * @return
     */
    @Override
    public List<OitUserResource> queryAll(OitUserResource oitUserResource) {
        return this.oitUserResourceDao.queryAll(oitUserResource);
    }
}