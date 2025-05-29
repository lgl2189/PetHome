package com.pethome.util;

import com.pethome.dto.PageInfo;

/**
 * @author ：Star
 * @description ：查询分页工具类
 * @date ：2025 5月 25 16:38
 */


public class DatabasePageUtil {
    public static <T> PageInfo getPageInfo(com.github.pagehelper.PageInfo<T> pageInfo) {
        PageInfo pageInfoWrapper = new PageInfo();
        // 当前页页数
        pageInfoWrapper.setPageNumNow(pageInfo.getPageNum());
        // 总页数
        pageInfoWrapper.setPageNumTotal(pageInfo.getPages());
        // 当前页记录数
        pageInfoWrapper.setPageSizeNow(pageInfo.getSize());
        // 每页记录数
        pageInfoWrapper.setPageSize(pageInfo.getPageSize());
        // 总记录数
        pageInfoWrapper.setRecordNum(pageInfo.getTotal());
        return pageInfoWrapper;
    }
}