package com.pethome.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pethome.entity.mybatis.MessageRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 21 17:13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @JsonIgnore
    private Integer messageId;

    @JsonUnwrapped
    private MessageRecord messageRecord;

    private String senderName;

    private String receiverName;
}