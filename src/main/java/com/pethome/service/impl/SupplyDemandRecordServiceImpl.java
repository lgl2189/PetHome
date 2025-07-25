package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.SupplyDemandRecord;
import com.pethome.mapper.SupplyDemandRecordMapper;
import com.pethome.service.SupplyDemandRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储物资需求记录 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class SupplyDemandRecordServiceImpl extends ServiceImpl<SupplyDemandRecordMapper, SupplyDemandRecord> implements SupplyDemandRecordService {

    @Override
    public PageInfo<SupplyDemandRecord> getAllSupplyDemandRecord(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list());
    }

    @Override
    public PageInfo<SupplyDemandRecord> getDemandListByInventoryId(Integer inventoryId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SupplyDemandRecord> query = new LambdaQueryWrapper<>();
        query.eq(SupplyDemandRecord::getInventoryId, inventoryId);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list(query));
    }

    @Override
    public PageInfo<SupplyDemandRecord> getDemandListByStation(Integer stationId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SupplyDemandRecord> query = new LambdaQueryWrapper<>();
        query.eq(SupplyDemandRecord::getRescueStationId, stationId);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list(query));
    }
}
