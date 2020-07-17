package com.oit.oitcloud.service;

import com.oit.oitcloud.dto.OrganizeDTO;
import com.oit.oitcloud.entity.OitOrganize;
import com.oit.oitcloud.entity.RestResponse;

import java.util.List;

/**
 * 组织结构表(OitOrganize)表服务接口
 *
 * @author guff
 * @since 2020-06-05 14:28:59
 */
public interface OitOrganizeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitOrganize queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitOrganize> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitOrganize 实例对象
     * @return 实例对象
     */
    OitOrganize insert(OitOrganize oitOrganize);

    /**
     * 修改数据
     *
     * @param oitOrganize 实例对象
     * @return 实例对象
     */
    OitOrganize update(OitOrganize oitOrganize);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     * @param oitOrganize
     * @return
     */
    List<OrganizeDTO> queryAll(OitOrganize oitOrganize);

    /**
     * 同步钉钉的部门列表
     * @return
     */
    RestResponse syncOrgan();
}