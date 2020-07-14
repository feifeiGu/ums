package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.dao.OitUserRoleRelDao;
import com.oit.oitcloud.entity.OitUserRoleRel;
import com.oit.oitcloud.service.OitUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关系表(OitUserRoleRel)表服务实现类
 *
 * @author guff
 * @since 2020-06-04 15:13:18
 */
@Service("oitUserRoleRelService")
public class OitUserRoleRelServiceImpl implements OitUserRoleRelService {
    @Resource
    private OitUserRoleRelDao oitUserRoleRelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitUserRoleRel queryById(Integer id) {
        return this.oitUserRoleRelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitUserRoleRel> queryAllByLimit(int offset, int limit) {
        return this.oitUserRoleRelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitUserRoleRel 实例对象
     * @return 实例对象
     */
    @Override
    public OitUserRoleRel insert(OitUserRoleRel oitUserRoleRel) {
        this.oitUserRoleRelDao.insert(oitUserRoleRel);
        return oitUserRoleRel;
    }

    /**
     * 修改数据
     *
     * @param oitUserRoleRel 实例对象
     * @return 实例对象
     */
    @Override
    public OitUserRoleRel update(OitUserRoleRel oitUserRoleRel) {
        this.oitUserRoleRelDao.update(oitUserRoleRel);
        return this.queryById(oitUserRoleRel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitUserRoleRelDao.deleteById(id) > 0;
    }

    /**
     * 通过实体作为筛选条件查询
     * @param oitUserRoleRel
     * @return
     */
    @Override
    public List<OitUserRoleRel> queryAll(OitUserRoleRel oitUserRoleRel) {
        return this.oitUserRoleRelDao.queryAll(oitUserRoleRel);
    }

    /**
     * 根据条件删除用户角色关系
     * @param oitUserRoleRel
     * @return
     */
    @Override
    public boolean deleteByCondition(OitUserRoleRel oitUserRoleRel) {
        return this.oitUserRoleRelDao.deleteByCondition(oitUserRoleRel) > 0;
    }
}