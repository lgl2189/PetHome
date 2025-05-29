package com.pethome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Star
 * @description ：包装分页信息的实体类，用于返回前端分页信息
 * @date ：2025 5月 25 16:40
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    /**
     * 当前页码
     */
    private int pageNumNow;
    /**
     * 总页数
     */
    private int pageNumTotal;
    /**
     * 当前页的记录数
     */
    private int pageSizeNow;
    /**
     * 每页的记录数
     */
    private int pageSize;
    /**
     * 总记录数，即数据库查询总条数
     */
    private long recordNum;
}