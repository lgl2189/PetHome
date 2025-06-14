package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Star
 * @description ：该类用于定义志愿者任务本身的状态枚举类
 * @date ：2025 6月 05 10:17
 */
@Getter
public enum VolunteerTaskStatusEnum {
    AVAILABLE("available"),
    ASSIGNED("assigned"),
    STARTED("started"),
    FINISH_REVIEWING("finish_reviewing"),
    COMPLETED("completed");

    @EnumValue
    private final String value;
    private static final Map<String, VolunteerTaskStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (VolunteerTaskStatusEnum type : values()) {
            VALUE_MAP.put(type.getValue(), type);
        }
    }

    VolunteerTaskStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回AVAILABLE
     *
     * @param value 枚举值
     * @return 枚举对象
     */
    public static VolunteerTaskStatusEnum fromValue(String value) {
        return value == null ? AVAILABLE : VALUE_MAP.getOrDefault(value, AVAILABLE);
    }

}