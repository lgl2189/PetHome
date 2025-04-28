package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 存储志愿者信息
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "volunteer_id", type = IdType.AUTO)
    private Integer volunteerId;

    private Integer userId;

    private Integer rescueStationId;

    private Integer point;
}
