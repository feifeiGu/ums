package com.oit.oitcloud.service;

import com.oit.oitcloud.entity.OitRoleResource;

import java.util.List;

/**
 * 角色资源表(OitRoleResource)表服务接口
 *
 * @author guff
 * @since 2020-06-04 16:06:17
 */
public interface OitRoleResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitRoleResource queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitRoleResource> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitRoleResource 实例对象
     * @return 实例对象
     */
    OitRoleResource insert(OitRoleResource oitRoleResource);

    /**
     * 修改数据
     *
     * @param oitRoleResource 实例对象
     * @return 实例对象
     */
    OitRoleResource update(OitRoleResource oitRoleResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     * @param oitRoleResource
     * @return
     */
    List<OitRoleResource> queryAll(OitRoleResource oitRoleResource);
}