package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.AdoptionBlack;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储领养黑名单 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Mapper
public interface AdoptionBlackMapper extends BaseMapper<AdoptionBlack> {

}
