package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.FileRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-05-16
 */
@Mapper
public interface FileRecordMapper extends BaseMapper<FileRecord> {
    long getMaxGroupId();
}
