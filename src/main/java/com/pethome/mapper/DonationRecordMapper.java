package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.DonationRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储捐赠记录 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Mapper
public interface DonationRecordMapper extends BaseMapper<DonationRecord> {

}
