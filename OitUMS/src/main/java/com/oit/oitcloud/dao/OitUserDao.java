package com.oit.oitcloud.dao;

import com.oit.oitcloud.entity.OitUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 用户表(OitUser)表数据库访问层
 *
 * @author guff
 * @since 2020-06-01 16:55:55
 */
@Mapper
public interface OitUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oitUser 实例对象
     * @return 对象列表
     */
    List<OitUser> queryAll(OitUser oitUser);

    /**
     * 新增数据
     *
     * @param oitUser 实例对象
     * @return 影响行数
     */
    int insert(OitUser oitUser);

    /**
     * 修改数据
     *
     * @param oitUser 实例对象
     * @return 影响行数
     */
    int update(OitUser oitUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过username删除数据
     * @param username
     * @return
     */
    int deleteByUsername(String username);

    /**
     * 通过username查询单条数据
     * @param username
     * @return
     */
    OitUser queryByUsername(String username);

    /**
     * 通过map作为筛选条件查询
     * @param map
     * @return
     */
    List<OitUser> queryAllByDepartment(Map map);
}