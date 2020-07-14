package com.oit.oitcloud.service;

import com.oit.oitcloud.dto.AppDTO;
import com.oit.oitcloud.entity.OitApp;

import java.util.List;

/**
 * 系统应用表(OitApp)表服务接口
 *
 * @author guff
 * @since 2020-06-03 11:05:39
 */
public interface OitAppService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OitApp queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OitApp> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oitApp 实例对象
     * @return 实例对象
     */
    OitApp insert(OitApp oitApp);

    /**
     * 修改数据
     *
     * @param oitApp 实例对象
     * @return 实例对象
     */
    OitApp update(OitApp oitApp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过appKey查询单条数据
     * @param appKey
     * @return
     */
    OitApp queryByAppKey(String appKey);

    /**
     * 查询应用列表
     * @param oitApp
     * @return
     */
    List<AppDTO> queryAll(OitApp oitApp);
}