package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.dto.Message;
import com.pethome.entity.mybatis.ChatRecord;
import com.pethome.mapper.ChatRecordMapper;
import com.pethome.service.ChatRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储用户间交流记录 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements ChatRecordService {

    @Override
    public PageInfo<Message> getRecentChatUserList(Integer userId, int pageNum, int pageSize) {
        if(pageNum > 0 && pageSize > 0) PageHelper.startPage(pageNum, pageSize);
        List<Message> recordList = this.baseMapper.selectRecentChatUserList(userId);
        return new PageInfo<>(recordList);
    }
}
