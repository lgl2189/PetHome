package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 领养申请状态枚举类
@Getter
public enum InventoryChangeTypeEnum {
    IN("in"),
    OUT("out");

    @EnumValue
    private final String value;
    private static final Map<String, InventoryChangeTypeEnum> VALUE_MAP = new HashMap<>();

    static {
        for (InventoryChangeTypeEnum status : InventoryChangeTypeEnum.values()) {
            VALUE_MAP.put(status.getValue(), status);
        }
    }

    InventoryChangeTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回IN
     * @param value 枚举值
     * @return 枚举对象
     */
    public static InventoryChangeTypeEnum fromValue(String value) {
        return VALUE_MAP.getOrDefault(value, IN);
    }


}