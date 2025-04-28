package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储救助站库存
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "inventory_id", type = IdType.AUTO)
    private Integer inventoryId;

    private Integer rescueStationId;

    private String supplyName;

    private Integer supplyQuantity;

    private Integer warningQuantity;
}
