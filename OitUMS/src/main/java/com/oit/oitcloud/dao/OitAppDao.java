package com.oit.oitcloud.dao;

import com.oit.oitcloud.entity.OitApp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统应用表(OitApp)表数据库访问层
 *
 * @author guff
 * @since 2020-06-03 11:05:39
 */
@Mapper
public interface OitAppDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitApp queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitApp> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oitApp 实例对象
     * @return 对象列表
     */
    List<OitApp> queryAll(OitApp oitApp);

    /**
     * 新增数据
     *
     * @param oitApp 实例对象
     * @return 影响行数
     */
    int insert(OitApp oitApp);

    /**
     * 修改数据
     *
     * @param oitApp 实例对象
     * @return 影响行数
     */
    int update(OitApp oitApp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过appKey查询单条数据
     * @param appKey
     * @return
     */
    OitApp queryByAppKey(String appKey);
}