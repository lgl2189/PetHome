package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.dto.Message;
import com.pethome.dto.MessageList;
import com.pethome.entity.mybatis.MessageRecord;

/**
 * <p>
 * 存储用户间交流记录 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface MessageRecordService extends IService<MessageRecord> {
    /**
     * 获取该userId的最近聊天的用户列表，如果pageNum或pageSize小于等于0，则返回全部数据
     *
     * @param userId   用户id
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 最近聊天列表
     */
    PageInfo<Message> getRecentChatUserList(Integer userId, int pageNum, int pageSize);

    /**
     * 获取senderId和receiverId的聊天记录列表
     *
     * @param userId1 发送者用户id
     * @param userId2 接收者用户id
     * @return 聊天记录列表
     */
    MessageList getRecentChatRecordList(Integer userId1, Integer userId2);
}
