package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.InventoryChangeRecord;
import com.pethome.mapper.InventoryChangeRecordMapper;
import com.pethome.service.InventoryChangeRecordService;
import org.springframework.stereotype.Service;

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

}
