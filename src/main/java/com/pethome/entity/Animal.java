package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pethome.entity.enums.AnimalHealthStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 存储动物信息
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "animal_id", type = IdType.AUTO)
    private Integer animalId;

    private String species;

    private String breed;

    private Boolean gender;

    private Integer age;

    private AnimalHealthStatusEnum healthStatus;

    private Boolean isSterilized;

    private String personality;

    private String description;

    private String imgUrl;

    private String videoUrl;

    private String vaccinationRecord;

    private String dewormingRecord;

    private String medicalReportUrl;

    private Boolean abnormalWarning;

    private Integer rescueStationId;
}
