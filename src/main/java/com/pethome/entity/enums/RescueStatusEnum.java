package com.pethome.entity.enums;

import lombok.Getter;

// 救助记录状态枚举类
@Getter
public enum RescueStatusEnum {
    WAITING_FOR_RESCUE("待救助"),
    IN_TREATMENT("治疗中"),
    WAITING_FOR_ADOPTION("待领养"),
    ADOPTED("已领养");

    private final String value;

    RescueStatusEnum(String value) {
        this.value = value;
    }

}
