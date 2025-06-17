package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储救助站信息
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueStation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "rescue_station_id", type = IdType.AUTO)
    private Integer rescueStationId;

    private String rescueStationName;

    private String position;

    private Integer adminUserId;

    private Long paymentQrcodeGid;
}