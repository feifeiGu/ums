package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.dao.OitAppDao;
import com.oit.oitcloud.dto.AppDTO;
import com.oit.oitcloud.entity.OitApp;
import com.oit.oitcloud.service.OitAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统应用表(OitApp)表服务实现类
 *
 * @author guff
 * @since 2020-06-03 11:05:39
 */
@Service("oitAppService")
public class OitAppServiceImpl implements OitAppService {
    @Resource
    private OitAppDao oitAppDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitApp queryById(Integer id) {
        return this.oitAppDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitApp> queryAllByLimit(int offset, int limit) {
        return this.oitAppDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitApp 实例对象
     * @return 实例对象
     */
    @Override
    public OitApp insert(OitApp oitApp) {
        oitApp.setStatus(OitUmsConstants.ENABLE);
        this.oitAppDao.insert(oitApp);
        return oitApp;
    }

    /**
     * 修改数据
     *
     * @param oitApp 实例对象
     * @return 实例对象
     */
    @Override
    public OitApp update(OitApp oitApp) {
        this.oitAppDao.update(oitApp);
        return this.queryById(oitApp.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitAppDao.deleteById(id) > 0;
    }

    /**
     * 通过appKey查询单条数据
     * @param appKey
     * @return
     */
    @Override
    public OitApp queryByAppKey(String appKey) {
        return this.oitAppDao.queryByAppKey(appKey);
    }

    /**
     * 查询应用列表
     * @param oitApp
     * @return
     */
    @Override
    public List<AppDTO> queryAll(OitApp oitApp) {
        List<OitApp> oitAppList = oitAppDao.queryAll(oitApp);
        List<AppDTO> appDTOList = oitAppList.stream().map(temp -> {
            AppDTO appDTO = new AppDTO();
            appDTO.setId(temp.getId());
            appDTO.setAppName(temp.getAppName());
            return appDTO;
        }).collect(Collectors.toList());
        return appDTOList;
    }
}