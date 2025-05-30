package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 救助记录状态枚举类
@Getter
public enum RescueStatusEnum {
    WAITING_FOR_RESCUE("wait_rescue"),
    UNDER_TREATMENT("under_treatment"),
    WAITING_FOR_ADOPTION("wait_adopted"),
    ADOPTED("adopted");

    @EnumValue
    private final String value;
    private static final Map<String, RescueStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (RescueStatusEnum type : values()) {
            VALUE_MAP.put(type.getValue(), type);
        }
    }

    RescueStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
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
