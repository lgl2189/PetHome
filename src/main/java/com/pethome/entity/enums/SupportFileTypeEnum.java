package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 16 16:36
 */

@Getter
public enum SupportFileTypeEnum {
    IMAGE("image"),
    VIDEO("video"),
    PDF("pdf"),
    TXT("txt"),
    OTHER("other");

    @EnumValue
    private final String value;
    private static final Map<String, SupportFileTypeEnum> VALUE_MAP = new HashMap<>();

    static {
        for (SupportFileTypeEnum type : values()) {
            VALUE_MAP.put(type.getValue(), type);
        }
    }

    SupportFileTypeEnum(String value) {
        this.value = value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回OTHER
     * @param value 枚举值
     * @return 枚举对象
     */
    public static SupportFileTypeEnum fromValue(String value) {
        return value == null ? OTHER : VALUE_MAP.getOrDefault(value, OTHER);
    }
}