package com.pethome.service.impl;

import com.pethome.entity.mybatis.Inventory;
import com.pethome.mapper.InventoryMapper;
import com.pethome.service.InventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
