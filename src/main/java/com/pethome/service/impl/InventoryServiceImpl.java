package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.Inventory;
import com.pethome.mapper.InventoryMapper;
import com.pethome.service.InventoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储救助站库存 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Override
    public PageInfo<Inventory> getInventoryByStation(Integer stationId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Inventory> query = new LambdaQueryWrapper<>();
        query.eq(Inventory::getRescueStationId, stationId);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list(query));
    }
}
