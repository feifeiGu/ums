package com.oit.oitcloud.service;

import com.oit.oitcloud.dto.RoleDTO;
import com.oit.oitcloud.dto.RoleResourceDTO;
import com.oit.oitcloud.entity.OitRole;
import com.oit.oitcloud.entity.RestResponse;

import java.util.List;

/**
 * 角色表(OitRole)表服务接口
 *
 * @author guff
 * @since 2020-06-04 14:06:43
 */
public interface OitRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitRole queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitRole 实例对象
     * @return 实例对象
     */
    OitRole insert(OitRole oitRole);

    /**
     * 修改数据
     *
     * @param oitRole 实例对象
     * @return 实例对象
     */
    OitRole update(OitRole oitRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 角色列表
     * @param oitRole
     * @return
     */
    List<RoleDTO> queryAll(OitRole oitRole);

    /**
     * 新增角色和角色资源
     * @param roleResourceDTO
     */
    RestResponse insertRoleResource(RoleResourceDTO roleResourceDTO);

    /**
     * 修改角色和角色资源
     * @param roleId
     * @param roleResourceDTO
     */
    RestResponse updateRoleResource(Integer roleId, RoleResourceDTO roleResourceDTO);

    /**
     * 获取角色和角色资源
     * @param id
     * @return
     */
    RoleResourceDTO queryRoleResource(Integer id);
}