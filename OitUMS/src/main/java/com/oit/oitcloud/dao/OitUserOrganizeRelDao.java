package com.oit.oitcloud.dao;

import com.oit.oitcloud.entity.OitUserOrganizeRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组织关系表(OitUserOrganizeRel)表数据库访问层
 *
 * @author guff
 * @since 2020-06-05 14:45:57
 */
@Mapper
public interface OitUserOrganizeRelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUserOrganizeRel queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUserOrganizeRel> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oitUserOrganizeRel 实例对象
     * @return 对象列表
     */
    List<OitUserOrganizeRel> queryAll(OitUserOrganizeRel oitUserOrganizeRel);

    /**
     * 新增数据
     *
     * @param oitUserOrganizeRel 实例对象
     * @return 影响行数
     */
    int insert(OitUserOrganizeRel oitUserOrganizeRel);

    /**
     * 修改数据
     *
     * @param oitUserOrganizeRel 实例对象
     * @return 影响行数
     */
    int update(OitUserOrganizeRel oitUserOrganizeRel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过organizeId删除数据
     * @param organizeId
     * @return
     */
    int deleteByOrganizeId(Integer organizeId);

    /**
     * 通过userId删除数据
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);

}