package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 存储用户间交流记录
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    private Integer senderId;

    private Integer receiverId;

    private String messageContent;

    private LocalDateTime messageDatetime;
}
