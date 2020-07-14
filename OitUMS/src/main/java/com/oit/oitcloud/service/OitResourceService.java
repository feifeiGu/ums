package com.oit.oitcloud.service;

import com.oit.oitcloud.dto.ResourceDTO;
import com.oit.oitcloud.entity.OitResource;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * 页面资源表(OitResource)表服务接口
 *
 * @author guff
 * @since 2020-06-05 13:21:08
 */
public interface OitResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitResource queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitResource> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitResource 实例对象
     * @return 实例对象
     */
    OitResource insert(OitResource oitResource);

    /**
     * 修改数据
     *
     * @param oitResource 实例对象
     * @return 实例对象
     */
    OitResource update(OitResource oitResource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     * @param oitResource
     * @return
     */
    List<OitResource> queryAll(OitResource oitResource);

    /**
     * 根据token获取用户菜单资源
     * @param authentication
     * @return
     */
    List<ResourceDTO> queryUserMenus(Authentication authentication);

    /**
     * 根据token和pid获取用户菜单按钮
     * @param authentication
     * @param pid
     * @return
     */
    List<ResourceDTO> queryUserMenuButtons(Authentication authentication, Integer pid);
}