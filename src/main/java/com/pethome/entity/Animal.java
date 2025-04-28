package com.pethome.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = 1L;

    @TableId(value = "animal_id", type = IdType.AUTO)
    private Integer animalId;

    private String species;

    private String breed;

    private Boolean gender;

    private Integer age;

    private String healthStatus;

    private Boolean isSterilized;

    private String personality;

    private String description;

    private String imgUrl;

    private String videoUrl;

    private String vaccinationRecord;

    private String dewormingRecord;

    private String medicalReportUrl;

    private Boolean abnormalWarning;

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Boolean getIsSterilized() {
        return isSterilized;
    }

    public void setIsSterilized(Boolean isSterilized) {
        this.isSterilized = isSterilized;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVaccinationRecord() {
        return vaccinationRecord;
    }

    public void setVaccinationRecord(String vaccinationRecord) {
        this.vaccinationRecord = vaccinationRecord;
    }

    public String getDewormingRecord() {
        return dewormingRecord;
    }

    public void setDewormingRecord(String dewormingRecord) {
        this.dewormingRecord = dewormingRecord;
    }

    public String getMedicalReportUrl() {
        return medicalReportUrl;
    }

    public void setMedicalReportUrl(String medicalReportUrl) {
        this.medicalReportUrl = medicalReportUrl;
    }

    public Boolean getAbnormalWarning() {
        return abnormalWarning;
    }

    public void setAbnormalWarning(Boolean abnormalWarning) {
        this.abnormalWarning = abnormalWarning;
    }

    @Override
    public String toString() {
        return "Animal{" +
        "animalId = " + animalId +
        ", species = " + species +
        ", breed = " + breed +
        ", gender = " + gender +
        ", age = " + age +
        ", healthStatus = " + healthStatus +
        ", isSterilized = " + isSterilized +
        ", personality = " + personality +
        ", description = " + description +
        ", imgUrl = " + imgUrl +
        ", videoUrl = " + videoUrl +
        ", vaccinationRecord = " + vaccinationRecord +
        ", dewormingRecord = " + dewormingRecord +
        ", medicalReportUrl = " + medicalReportUrl +
        ", abnormalWarning = " + abnormalWarning +
        "}";
    }
}
