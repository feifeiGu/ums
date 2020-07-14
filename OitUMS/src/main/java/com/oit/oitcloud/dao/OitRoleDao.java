package com.oit.oitcloud.dao;

import com.oit.oitcloud.entity.OitRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表(OitRole)表数据库访问层
 *
 * @author guff
 * @since 2020-06-04 14:06:43
 */
@Mapper
public interface OitRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitRole queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oitRole 实例对象
     * @return 对象列表
     */
    List<OitRole> queryAll(OitRole oitRole);

    /**
     * 通过实体作为筛选条件查询
     * @param oitRole
     * @return
     */
    List<OitRole> queryByCondition(OitRole oitRole);

    /**
     * 新增数据
     *
     * @param oitRole 实例对象
     * @return 影响行数
     */
    int insert(OitRole oitRole);

    /**
     * 修改数据
     *
     * @param oitRole 实例对象
     * @return 影响行数
     */
    int update(OitRole oitRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}