package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.Inventory;

/**
 * <p>
 * 存储救助站库存 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface InventoryService extends IService<Inventory> {
    /**
     * 根据救助站id获取库存列表
     * @param stationId 救助站id
     * @param pageNum 当前页码
     * @param pageSize 每一页总条数
     * @return 库存列表
     */
    PageInfo<Inventory> getInventoryByStation(Integer stationId, Integer pageNum, Integer pageSize);
}
