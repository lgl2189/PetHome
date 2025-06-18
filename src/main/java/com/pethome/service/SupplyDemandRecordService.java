package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.SupplyDemandRecord;

/**
 * <p>
 * 存储物资需求记录 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface SupplyDemandRecordService extends IService<SupplyDemandRecord> {
    /**
     * 获取所有需求记录
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录条数
     * @return 需求记录列表
     */
    PageInfo<SupplyDemandRecord> getAllSupplyDemandRecord(Integer pageNum, Integer pageSize);

    /**
     * 获取一个物资的所有需求记录
     * @param inventoryId 库存ID
     * @param pageNum 当前页码
     * @param pageSize 每页记录条数
     * @return 需求记录列表
     */
    PageInfo<SupplyDemandRecord> getDemandListByInventoryId(Integer inventoryId, Integer pageNum, Integer pageSize);

    /**
     * 获取一个供应站的所有需求记录
     * @param stationId 供应站ID
     * @param pageNum 当前页码
     * @param pageSize 每页记录条数
     * @return 需求记录列表
     */
    PageInfo<SupplyDemandRecord> getDemandListByStation(Integer stationId, Integer pageNum, Integer pageSize);
}
