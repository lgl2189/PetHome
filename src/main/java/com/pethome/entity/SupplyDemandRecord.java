package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pethome.entity.enums.SupplyDemandStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储物资需求记录
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyDemandRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "supply_demand_id", type = IdType.AUTO)
    private Integer supplyDemandId;

    private Integer rescueStationId;

    private String supplyName;

    private Integer demandQuantity;

    private SupplyDemandStatusEnum demandStatus;
}
