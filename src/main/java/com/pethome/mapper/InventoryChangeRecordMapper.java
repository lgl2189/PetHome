package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.InventoryChangeRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储库存变动记录 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-06-18
 */
@Mapper
public interface InventoryChangeRecordMapper extends BaseMapper<InventoryChangeRecord> {

}
