package com.pethome.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pethome.entity.mybatis.ChatRecord;
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

    @JsonUnwrapped
    private ChatRecord chatRecord;

    private String userNameFrom;

    private String userNameTo;
}