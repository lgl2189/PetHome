package com.pethome.dto.receiver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 11 20:01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalReceiver implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String species;

    private String breed;

    /**
     * false-雌性，true雄性
     */
    private Boolean gender;

    private Integer age;

    private String healthStatus;

    private Boolean isSterilized;

    private String description;

    private String personality;

    private MultipartFile[] imageArray;

    private MultipartFile[] videoArray;

    private MultipartFile[] vaccinationRecordArray;

    private MultipartFile[] dewormingRecordArray;

    private MultipartFile[] medicalReportArray;

    private Integer rescueStationId;
}