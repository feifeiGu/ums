package com.oit.oitcloud.controller;

import com.oit.oitcloud.service.OitUserOrganizeRelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户组织关系表(OitUserOrganizeRel)表控制层
 *
 * @author guff
 * @since 2020-06-05 14:52:26
 */
@Api(tags = "用户组织关系管理")
@RestController
@RequestMapping("oitUserOrganizeRel")
public class OitUserOrganizeRelController {
    /**
     * 服务对象
     */
    @Resource
    private OitUserOrganizeRelService oitUserOrganizeRelService;

}