package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.CommunicationRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储用户间交流记录 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Mapper
public interface CommunicationRecordMapper extends BaseMapper<CommunicationRecord> {

}
