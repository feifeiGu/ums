package com.oit.oitcloud.service;

import com.oit.oitcloud.dto.UserResourceDTO;
import com.oit.oitcloud.entity.OitUser;
import com.oit.oitcloud.entity.PageRequest;
import com.oit.oitcloud.entity.PageResult;
import com.oit.oitcloud.entity.RestResponse;

import java.util.List;

/**
 * 用户表(OitUser)表服务接口
 *
 * @author guff
 * @since 2020-06-01 16:55:55
 */
public interface OitUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitUser 实例对象
     * @return 实例对象
     */
    OitUser insert(OitUser oitUser);

    /**
     * 修改数据
     *
     * @param oitUser 实例对象
     * @return 实例对象
     */
    OitUser update(OitUser oitUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过username删除数据
     * @param username
     * @return
     */
    boolean deleteByUsername(String username);

    /**
     * 通过username查询单条数据
     * @param username
     * @return
     */
    OitUser queryByUsername(String username);

    /**
     * 通过实体作为筛选条件查询
     * @param oitUser
     * @return
     */
    List<OitUser> queryAll(OitUser oitUser);

    /**
     * 新增用户和用户资源
     * @param userResourceDTO
     */
    RestResponse insertUserResource(UserResourceDTO userResourceDTO);

    /**
     * 修改用户和用户资源
     * @param userId
     * @param userResourceDTO
     */
    RestResponse updateUserResource(Integer userId, UserResourceDTO userResourceDTO);

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @param oitUser 实体类条件参数
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest, OitUser oitUser);

    /**
     * 同步钉钉的用户信息
     * @return
     */
    RestResponse syncUser();

    /**
     * 获取用户及用户资源
     * @param id
     * @return
     */
    UserResourceDTO queryUserResource(Integer id);

    /**
     * 根据部门ID获取用户列表分页接口
     * @param pageRequest
     * @param oitUser
     * @param departmentId
     * @return
     */
    PageResult findPageByDepartment(PageRequest pageRequest, OitUser oitUser, Integer departmentId);
}