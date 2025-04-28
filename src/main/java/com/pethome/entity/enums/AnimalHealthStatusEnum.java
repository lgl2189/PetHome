package com.pethome.entity.enums;

import lombok.Getter;

// 动物健康状态枚举类
@Getter
public enum AnimalHealthStatusEnum {
    HEALTHY("健康"),
    ILL("患病"),
    RECOVERING("康复中"),
    CRITICAL("危急");

    private final String value;

    AnimalHealthStatusEnum(String value) {
        this.value = value;
    }

}
