package com.pethome.entity.mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pethome.entity.enums.AdoptionApplicationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储领养信息
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionApplication implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "adoption_id", type = IdType.AUTO)
    private Integer adoptionId;

    private Integer animalId;

    private Integer adoptorId;

    private String career;

    private String houseCondition;

    private String experience;

    private AdoptionApplicationStatusEnum applicationStatus;
}
