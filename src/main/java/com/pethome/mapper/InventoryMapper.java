package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.Inventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储救助站库存 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {

}
