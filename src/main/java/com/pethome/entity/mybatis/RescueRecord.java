package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pethome.entity.enums.RescueStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 存储救助相关内容
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "rescue_id", type = IdType.AUTO)
    private Integer rescueId;

    private Integer animalId;

    private String rescuePosition;

    private LocalDateTime rescueDatetime;

    private String rescuerPhone;

    private RescueStatusEnum rescueStatus;

    private Integer rescueStationId;

    private String extraInfo;
}
