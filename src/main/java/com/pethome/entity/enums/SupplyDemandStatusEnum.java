package com.pethome.entity.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 物资需求状态枚举类
@Getter
public enum SupplyDemandStatusEnum {
    INCOMPLETE("未完成"),
    COMPLETE("已完成");

    private final String value;
    private static final Map<String, SupplyDemandStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (SupplyDemandStatusEnum type : values()) {
            VALUE_MAP.put(type.value, type);
        }
    }

    SupplyDemandStatusEnum(String value) {
        this.value = value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回INCOMPLETE
     * @param value 枚举值
     * @return 枚举对象
     */
    public static SupplyDemandStatusEnum fromValue(String value) {
        return value == null ? INCOMPLETE : VALUE_MAP.getOrDefault(value, INCOMPLETE);
    }

}