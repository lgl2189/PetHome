package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pethome.entity.enums.InventoryChangeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 存储库存变动记录
 * </p>
 *
 * @author lgl
 * @since 2025-06-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryChangeRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "change_record_id", type = IdType.AUTO)
    private Integer changeRecordId;

    private Integer rescueStationId;

    private Integer inventoryId;

    private String purpose;

    private Integer changeNum;

    private InventoryChangeTypeEnum changeType;

    private LocalDateTime changeDatetime;

    private Integer donationId;
}
