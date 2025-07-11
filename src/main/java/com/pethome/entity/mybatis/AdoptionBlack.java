package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储领养黑名单
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("adoption_black")
public class AdoptionBlack implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Integer userId;

    private Integer rescueStationId;

    private String extraInfo;
}
