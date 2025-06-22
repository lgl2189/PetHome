package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.dto.Message;
import com.pethome.dto.MessageList;
import com.pethome.entity.mybatis.MessageRecord;
import com.pethome.mapper.MessageRecordMapper;
import com.pethome.service.MessageRecordService;
import com.pethome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
public class MessageRecordServiceImpl extends ServiceImpl<MessageRecordMapper, MessageRecord> implements MessageRecordService {

    private final UserService userService;
    private final MessageRecordMapper messageRecordMapper;

    @Autowired
    public MessageRecordServiceImpl(UserService userService, MessageRecordMapper messageRecordMapper) {
        Assert.notNull(userService, "userService must not be null");
        Assert.notNull(messageRecordMapper, "messageRecordMapper must not be null");
        this.userService = userService;
        this.messageRecordMapper = messageRecordMapper;
    }

    @Override
    public PageInfo<Message> getRecentChatUserList(Integer userId, int pageNum, int pageSize) {
        if (pageNum > 0 && pageSize > 0) PageHelper.startPage(pageNum, pageSize);
        List<com.pethome.dto.Message> recordList = messageRecordMapper.selectRecentChatUserList(userId);
        return new PageInfo<>(recordList);
    }

    @Override
    public MessageList getRecentChatRecordList(Integer userId1, Integer userId2) {
        MessageList messageList = new MessageList(userId1, userId2);
        LambdaQueryWrapper<MessageRecord> query = new LambdaQueryWrapper<>();
        query.or(wrapper -> wrapper
                        .eq(MessageRecord::getSenderId, userId1)
                        .eq(MessageRecord::getReceiverId, userId2))
                .or(wrapper -> wrapper
                        .eq(MessageRecord::getSenderId, userId2)
                        .eq(MessageRecord::getReceiverId, userId1));
        messageList.setMessageList(messageRecordMapper.selectList(query));
        messageList.setSenderName(userService.getUserName(userId1));
        messageList.setReceiverName(userService.getUserName(userId2));
        return messageList;
    }

}
