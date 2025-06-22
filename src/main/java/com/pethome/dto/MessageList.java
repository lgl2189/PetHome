package com.pethome.dto;

import com.pethome.entity.mybatis.MessageRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 22 13:03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageList {
    private List<MessageRecord> messageList;

    private Integer senderId;

    private Integer receiverId;

    private String senderName;

    private String receiverName;

    private LocalDateTime groupStartDatetime;

    private LocalDateTime groupEndDatetime;

    public MessageList(Integer receiverId, Integer senderId) {
        this.receiverId = receiverId;
        this.senderId = senderId;
    }
}