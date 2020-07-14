package com.oit.oitcloud.dao;

import com.oit.oitcloud.entity.OitUserRoleRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关系表(OitUserRoleRel)表数据库访问层
 *
 * @author guff
 * @since 2020-06-04 15:13:18
 */
@Mapper
public interface OitUserRoleRelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUserRoleRel queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUserRoleRel> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oitUserRoleRel 实例对象
     * @return 对象列表
     */
    List<OitUserRoleRel> queryAll(OitUserRoleRel oitUserRoleRel);

    /**
     * 新增数据
     *
     * @param oitUserRoleRel 实例对象
     * @return 影响行数
     */
    int insert(OitUserRoleRel oitUserRoleRel);

    /**
     * 修改数据
     *
     * @param oitUserRoleRel 实例对象
     * @return 影响行数
     */
    int update(OitUserRoleRel oitUserRoleRel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据条件删除用户角色关系
     * @param oitUserRoleRel
     * @return
     */
    int deleteByCondition(OitUserRoleRel oitUserRoleRel);

}