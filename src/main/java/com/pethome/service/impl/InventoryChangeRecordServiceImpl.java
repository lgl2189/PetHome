package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.enums.InventoryChangeTypeEnum;
import com.pethome.entity.mybatis.Inventory;
import com.pethome.entity.mybatis.InventoryChangeRecord;
import com.pethome.mapper.InventoryChangeRecordMapper;
import com.pethome.service.InventoryChangeRecordService;
import com.pethome.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 存储库存变动记录 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-06-18
 */
@Service
public class InventoryChangeRecordServiceImpl extends ServiceImpl<InventoryChangeRecordMapper, InventoryChangeRecord> implements InventoryChangeRecordService {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryChangeRecordServiceImpl(InventoryService inventoryService) {
        Assert.notNull(inventoryService, "InventoryService must not be null");
        this.inventoryService = inventoryService;
    }

    @Override
    public PageInfo<InventoryChangeRecord> getRecordByStation(Integer stationId, int pageNum, int pageSize) {
        LambdaQueryWrapper<InventoryChangeRecord> query = new LambdaQueryWrapper<>();
        query.eq(InventoryChangeRecord::getRescueStationId, stationId);
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(this.list(query));
    }

    @Override
    public boolean addInventoryChangeRecord(InventoryChangeRecord inventoryChangeRecord) {
        Inventory inventory = inventoryService.getById(inventoryChangeRecord.getInventoryId());
        if (inventoryChangeRecord.getChangeType() == InventoryChangeTypeEnum.IN) {
            inventory.setInventoryQuantity(inventory.getInventoryQuantity() + inventoryChangeRecord.getChangeNum());
        }
        else if (inventoryChangeRecord.getChangeType() == InventoryChangeTypeEnum.OUT) {
            inventory.setInventoryQuantity(inventory.getInventoryQuantity() - inventoryChangeRecord.getChangeNum());
        }
        // 正常来说应该使用事务
        return inventoryService.updateById(inventory) && this.save(inventoryChangeRecord);
    }
}
