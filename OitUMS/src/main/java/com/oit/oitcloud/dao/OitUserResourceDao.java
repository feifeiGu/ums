package com.oit.oitcloud.dao;

import com.oit.oitcloud.entity.OitUserResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户资源表(OitUserResource)表数据库访问层
 *
 * @author guff
 * @since 2020-06-04 11:47:17
 */
@Mapper
public interface OitUserResourceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUserResource queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUserResource> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oitUserResource 实例对象
     * @return 对象列表
     */
    List<OitUserResource> queryAll(OitUserResource oitUserResource);

    /**
     * 新增数据
     *
     * @param oitUserResource 实例对象
     * @return 影响行数
     */
    int insert(OitUserResource oitUserResource);

    /**
     * 修改数据
     *
     * @param oitUserResource 实例对象
     * @return 影响行数
     */
    int update(OitUserResource oitUserResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据userId删除用户资源
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);
}