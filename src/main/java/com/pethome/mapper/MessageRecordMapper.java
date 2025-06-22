package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.MessageRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 存储用户间交流记录 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Mapper
public interface MessageRecordMapper extends BaseMapper<MessageRecord> {
    List<com.pethome.dto.Message> selectRecentChatUserList(Integer userId);
}
