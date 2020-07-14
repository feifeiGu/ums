package com.oit.oitcloud.service;

import com.oit.oitcloud.entity.OitUserOrganizeRel;

import java.util.List;

/**
 * 用户组织关系表(OitUserOrganizeRel)表服务接口
 *
 * @author guff
 * @since 2020-06-05 14:45:57
 */
public interface OitUserOrganizeRelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUserOrganizeRel queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUserOrganizeRel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitUserOrganizeRel 实例对象
     * @return 实例对象
     */
    OitUserOrganizeRel insert(OitUserOrganizeRel oitUserOrganizeRel);

    /**
     * 修改数据
     *
     * @param oitUserOrganizeRel 实例对象
     * @return 实例对象
     */
    OitUserOrganizeRel update(OitUserOrganizeRel oitUserOrganizeRel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     * @param oitUserOrganizeRel
     * @return
     */
    List<OitUserOrganizeRel> queryAll(OitUserOrganizeRel oitUserOrganizeRel);
}