package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pethome.entity.enums.VolunteerTaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 存储志愿者任务本身的信息
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerTask implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    private Integer rescueStationId;

    private LocalDateTime taskDatetime;

    private String taskPosition;

    private String taskContent;

    private VolunteerTaskStatusEnum taskStatus;

    private LocalTime taskDuration;

    private Integer taskPoint;
}
