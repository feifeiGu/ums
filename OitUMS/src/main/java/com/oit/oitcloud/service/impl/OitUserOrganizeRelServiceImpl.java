package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.dao.OitUserOrganizeRelDao;
import com.oit.oitcloud.entity.OitUserOrganizeRel;
import com.oit.oitcloud.service.OitUserOrganizeRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户组织关系表(OitUserOrganizeRel)表服务实现类
 *
 * @author guff
 * @since 2020-06-05 14:45:57
 */
@Service("oitUserOrganizeRelService")
public class OitUserOrganizeRelServiceImpl implements OitUserOrganizeRelService {
    @Resource
    private OitUserOrganizeRelDao oitUserOrganizeRelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitUserOrganizeRel queryById(Integer id) {
        return this.oitUserOrganizeRelDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitUserOrganizeRel> queryAllByLimit(int offset, int limit) {
        return this.oitUserOrganizeRelDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitUserOrganizeRel 实例对象
     * @return 实例对象
     */
    @Override
    public OitUserOrganizeRel insert(OitUserOrganizeRel oitUserOrganizeRel) {
        this.oitUserOrganizeRelDao.insert(oitUserOrganizeRel);
        return oitUserOrganizeRel;
    }

    /**
     * 修改数据
     *
     * @param oitUserOrganizeRel 实例对象
     * @return 实例对象
     */
    @Override
    public OitUserOrganizeRel update(OitUserOrganizeRel oitUserOrganizeRel) {
        this.oitUserOrganizeRelDao.update(oitUserOrganizeRel);
        return this.queryById(oitUserOrganizeRel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitUserOrganizeRelDao.deleteById(id) > 0;
    }
    
    /**
     * 通过实体作为筛选条件查询
     * @param oitUserOrganizeRel
     * @return
     */
    @Override
    public List<OitUserOrganizeRel> queryAll(OitUserOrganizeRel oitUserOrganizeRel) {
        return this.oitUserOrganizeRelDao.queryAll(oitUserOrganizeRel);
    }
}