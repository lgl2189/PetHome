package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.dto.Message;
import com.pethome.entity.mybatis.ChatRecord;
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
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {
    List<Message> selectRecentChatUserList(Integer userId);
}
