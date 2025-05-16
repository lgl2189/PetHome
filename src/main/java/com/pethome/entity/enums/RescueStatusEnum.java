package com.pethome.entity.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 救助记录状态枚举类
@Getter
public enum RescueStatusEnum {
    WAITING_FOR_RESCUE("待救助"),
    IN_TREATMENT("治疗中"),
    WAITING_FOR_ADOPTION("待领养"),
    ADOPTED("已领养");

    private final String value;
    private static final Map<String, RescueStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (RescueStatusEnum type : values()) {
            VALUE_MAP.put(type.value, type);
        }
    }

    RescueStatusEnum(String value) {
        this.value = value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回WAITING_FOR_RESCUE
     * @param value 枚举值
     * @return 枚举对象
     */
    public static RescueStatusEnum fromValue(String value) {
        return value == null ? WAITING_FOR_RESCUE : VALUE_MAP.getOrDefault(value, WAITING_FOR_RESCUE);
    }



}
