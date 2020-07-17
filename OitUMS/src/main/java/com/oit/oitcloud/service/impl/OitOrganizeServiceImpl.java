package com.oit.oitcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.dao.OitOrganizeDao;
import com.oit.oitcloud.dao.OitUserOrganizeRelDao;
import com.oit.oitcloud.dto.OrganizeDTO;
import com.oit.oitcloud.entity.OitOrganize;
import com.oit.oitcloud.entity.OitUserOrganizeRel;
import com.oit.oitcloud.entity.RestResponse;
import com.oit.oitcloud.enums.ResultCode;
import com.oit.oitcloud.service.OitOrganizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 组织结构表(OitOrganize)表服务实现类
 *
 * @author guff
 * @since 2020-06-05 14:28:59
 */
@Service("oitOrganizeService")
public class OitOrganizeServiceImpl implements OitOrganizeService {

    private static final Logger LOG = LoggerFactory.getLogger(OitOrganizeServiceImpl.class);

    @Value("${dingtalk.appKey}")
    private String dingtalk_appKey;
    @Value("${dingtalk.appSecret}")
    private String dingtalk_appSecret;
    @Value("${dingtalk.getToken}")
    private String dingtalk_getToken;
    @Value("${dingtalk.getDepartments}")
    private String dingtalk_getDepartments;

    @Resource
    private OitOrganizeDao oitOrganizeDao;
    @Resource
    private OitUserOrganizeRelDao oitUserOrganizeRelDao;

    @Resource
    private CallRestServiceImpl callRestService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OitOrganize queryById(Integer id) {
        return this.oitOrganizeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OitOrganize> queryAllByLimit(int offset, int limit) {
        return this.oitOrganizeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oitOrganize 实例对象
     * @return 实例对象
     */
    @Override
    public OitOrganize insert(OitOrganize oitOrganize) {
        this.oitOrganizeDao.insert(oitOrganize);
        return oitOrganize;
    }

    /**
     * 修改数据
     *
     * @param oitOrganize 实例对象
     * @return 实例对象
     */
    @Override
    public OitOrganize update(OitOrganize oitOrganize) {
        this.oitOrganizeDao.update(oitOrganize);
        return this.queryById(oitOrganize.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oitOrganizeDao.deleteById(id) > 0;
    }
    
    /**
     * 通过实体作为筛选条件查询
     * @param oitOrganize
     * @return
     */
    @Override
    public List<OrganizeDTO> queryAll(OitOrganize oitOrganize) {
        List<OrganizeDTO> organizeDTOList = new ArrayList<>();
        List<OitOrganize> oitOrganizeList = oitOrganizeDao.queryAll(oitOrganize);
        for(OitOrganize organize : oitOrganizeList){
            OrganizeDTO organizeDTO = new OrganizeDTO();
            organizeDTO.setId(organize.getId());
            organizeDTO.setName(organize.getName());
            organizeDTO.setPid(organize.getPid());
            organizeDTO.setRid(organize.getRid());
            OitUserOrganizeRel oitUserOrganizeRel = new OitUserOrganizeRel();
            if(organize.getId() == 1){
                organizeDTO.setReleUserSize(oitUserOrganizeRelDao.queryAll(oitUserOrganizeRel).size());
            }else{
                oitUserOrganizeRel.setOrganizeId(organize.getId());
                organizeDTO.setReleUserSize(oitUserOrganizeRelDao.queryAll(oitUserOrganizeRel).size());
            }
            organizeDTOList.add(organizeDTO);
        }
        return organizeDTOList;
    }

    /**
     * 同步钉钉的部门列表
     * @return
     */
    @Override
    public RestResponse syncOrgan() {
        List<OitOrganize> oitOrganizeList = new ArrayList<>();
        //调用钉钉API获取token接口
        String tokenUrl = MessageFormat.format(dingtalk_getToken,dingtalk_appKey,dingtalk_appSecret);
        RestResponse response = callRestService.restTemplateCallGet(tokenUrl);
        if(response.getCode() == ResultCode.SUCCESS.getCode()){
            JSONObject jsonObj = JSON.parseObject(response.getData().toString());
            String accessToken = jsonObj.getString("access_token");
            //调用钉钉API获取部门列表接口
            String departmentUrl = MessageFormat.format(dingtalk_getDepartments,accessToken);
            RestResponse departResponse = callRestService.restTemplateCallGet(departmentUrl);
            if(departResponse.getCode() == ResultCode.SUCCESS.getCode()){
                JSONObject departJsonObj = JSON.parseObject(departResponse.getData().toString());
                JSONArray jsonArray = (JSONArray)departJsonObj.get("department");
                //部门列表信息同步到mysql
                for(int i=0;i<jsonArray.size();i++) {
                    OitOrganize oitOrganize = new OitOrganize();
                    oitOrganize.setId(Integer.valueOf(jsonArray.getJSONObject(i).get("id").toString()));
                    oitOrganize.setName(jsonArray.getJSONObject(i).get("name").toString());
                    if(jsonArray.getJSONObject(i).get("parentid") != null){
                        LOG.info("=====parentid=====:{}",jsonArray.getJSONObject(i).get("parentid"));
                        oitOrganize.setPid(Integer.valueOf(jsonArray.getJSONObject(i).get("parentid").toString()));
                    }
                    oitOrganize.setRid(OitUmsConstants.DINGDING_ROOTID);
                    oitOrganize.setCreateBy(OitUmsConstants.CREATE_BY);
                    oitOrganizeDao.deleteById(oitOrganize.getId());
                    oitOrganizeDao.insert(oitOrganize);
                    oitOrganizeList.add(oitOrganize);
                }
            }
        }
        return RestResponse.succuess(oitOrganizeList);
    }
}