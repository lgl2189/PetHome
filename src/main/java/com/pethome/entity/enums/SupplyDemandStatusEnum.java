package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 物资需求状态枚举类
@Getter
public enum SupplyDemandStatusEnum {
    UNFINISHED("unfinished"),
    FINISHED("finished");

    @EnumValue
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
        return value == null ? UNFINISHED : VALUE_MAP.getOrDefault(value, UNFINISHED);
    }

}