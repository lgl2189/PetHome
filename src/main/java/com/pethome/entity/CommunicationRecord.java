package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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
public class CommunicationRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "communication_id", type = IdType.AUTO)
    private Integer communicationId;

    private Integer user_id_from;

    private Integer user_id_to;

    private LocalDate communicationDate;

    private String communicationContent;
}
