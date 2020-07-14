package com.oit.oitcloud.dao;

import com.oit.oitcloud.entity.OitOrganize;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 组织结构表(OitOrganize)表数据库访问层
 *
 * @author guff
 * @since 2020-06-05 14:28:59
 */
@Mapper
public interface OitOrganizeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitOrganize queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitOrganize> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oitOrganize 实例对象
     * @return 对象列表
     */
    List<OitOrganize> queryAll(OitOrganize oitOrganize);

    /**
     * 新增数据
     *
     * @param oitOrganize 实例对象
     * @return 影响行数
     */
    int insert(OitOrganize oitOrganize);

    /**
     * 修改数据
     *
     * @param oitOrganize 实例对象
     * @return 影响行数
     */
    int update(OitOrganize oitOrganize);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}