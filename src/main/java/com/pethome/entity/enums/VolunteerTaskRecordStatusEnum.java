package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Star
 * @description ：该任务记录的是一个志愿者的一个任务的状态
 * @date ：2025 6月 05 10:17
 */

@Getter
public enum VolunteerTaskRecordStatusEnum {
    UNFINISHED("unfinished"),
    FINISH_REVIEWING("finish_reviewing"),
    COMPLETED("completed"),
    FAILED("failed");

    @EnumValue
    private final String value;
    private static final Map<String, VolunteerTaskRecordStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (VolunteerTaskRecordStatusEnum type : values()) {
            VALUE_MAP.put(type.getValue(), type);
        }
    }

    VolunteerTaskRecordStatusEnum(String value) {
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
    public static VolunteerTaskRecordStatusEnum fromValue(String value) {
        return value == null ? UNFINISHED : VALUE_MAP.getOrDefault(value, UNFINISHED);
    }
}