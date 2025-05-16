package com.pethome.entity.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 动物健康状态枚举类
@Getter
public enum AnimalHealthStatusEnum{
    HEALTHY("健康"),
    ILL("患病"),
    RECOVERING("康复中"),
    CRITICAL("危急");

    private final String value;
    private static final Map<String, AnimalHealthStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (AnimalHealthStatusEnum type : values()) {
            VALUE_MAP.put(type.value, type);
        }
    }

    AnimalHealthStatusEnum(String value) {
        this.value = value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回HEALTHY
     * @param value 枚举值
     * @return 枚举对象
     */
    public static AnimalHealthStatusEnum fromValue(String value) {
        return value == null ? HEALTHY : VALUE_MAP.getOrDefault(value, HEALTHY);
    }

}
