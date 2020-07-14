package com.oit.oitcloud.service;

import com.oit.oitcloud.entity.OitUserRoleRel;

import java.util.List;

/**
 * 用户角色关系表(OitUserRoleRel)表服务接口
 *
 * @author guff
 * @since 2020-06-04 15:13:18
 */
public interface OitUserRoleRelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUserRoleRel queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUserRoleRel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitUserRoleRel 实例对象
     * @return 实例对象
     */
    OitUserRoleRel insert(OitUserRoleRel oitUserRoleRel);

    /**
     * 修改数据
     *
     * @param oitUserRoleRel 实例对象
     * @return 实例对象
     */
    OitUserRoleRel update(OitUserRoleRel oitUserRoleRel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     * @param oitUserRoleRel
     * @return
     */
    List<OitUserRoleRel> queryAll(OitUserRoleRel oitUserRoleRel);

    /**
     * 根据条件删除用户角色关系
     * @param oitUserRoleRel
     * @return
     */
    boolean deleteByCondition(OitUserRoleRel oitUserRoleRel);
}