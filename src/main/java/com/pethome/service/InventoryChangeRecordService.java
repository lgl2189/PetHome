package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.InventoryChangeRecord;

/**
 * <p>
 * 存储库存变动记录 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-06-18
 */
public interface InventoryChangeRecordService extends IService<InventoryChangeRecord> {
    /**
     * 根据仓救助站ID获取库存变动记录
     * @param stationId 救助站ID
     * @param pageNum 当前页码
     * @param pageSize 每页包含的记录数
     * @return 库存变动记录
     */
    PageInfo<InventoryChangeRecord> getRecordByStation(Integer stationId, int pageNum, int pageSize);
}
