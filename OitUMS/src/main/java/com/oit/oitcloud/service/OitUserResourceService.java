package com.oit.oitcloud.service;

import com.oit.oitcloud.entity.OitUserResource;

import java.util.List;

/**
 * 用户资源表(OitUserResource)表服务接口
 *
 * @author guff
 * @since 2020-06-04 11:47:17
 */
public interface OitUserResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUserResource queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUserResource> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitUserResource 实例对象
     * @return 实例对象
     */
    OitUserResource insert(OitUserResource oitUserResource);

    /**
     * 修改数据
     *
     * @param oitUserResource 实例对象
     * @return 实例对象
     */
    OitUserResource update(OitUserResource oitUserResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过userId获取resource资源
     * @param oitUserResource
     * @return
     */
    List<OitUserResource> queryAll(OitUserResource oitUserResource);
}