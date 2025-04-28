package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 存储捐赠记录
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "donation_id", type = IdType.AUTO)
    private Integer donationId;

    private Integer userId;

    private LocalDate donationDate;

    private Integer donationMoney;
}
