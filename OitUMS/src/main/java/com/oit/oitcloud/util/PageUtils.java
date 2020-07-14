package com.oit.oitcloud.util;

import com.github.pagehelper.PageInfo;
import com.oit.oitcloud.entity.PageRequest;
import com.oit.oitcloud.entity.PageResult;

public class PageUtils {

    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
