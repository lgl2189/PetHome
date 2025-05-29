package com.pethome.dto.sender;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pethome.entity.mybatis.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author ：Star
 * @description ：用于包装返回给前端的动物信息
 * @date ：2025 5月 26 15:59
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSender implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private Animal animalInfo;

    private List<String> imgUrlList;

    private List<String> videoUrlList;

    private List<String> vaccinationRecordUrlList;

    private List<String> dewormingRecordUrlList;

    private List<String> medicalReportUrlList;

    public AnimalSender(Animal animalInfo) {
        this.animalInfo = animalInfo;
    }

}