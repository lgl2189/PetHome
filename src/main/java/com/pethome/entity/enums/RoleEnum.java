package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum RoleEnum {
    SUPER("super"),
    ADMIN("admin"),
    VOLUNTEER("volunteer"),
    ADOPTER("adopter"),
    DONATOR("donator"),
    NORMAL("normal");

    @EnumValue
    private final String value;
    private static final Map<String, RoleEnum> VALUE_MAP = new HashMap<>();

    static {
        for (RoleEnum role : RoleEnum.values()) {
            VALUE_MAP.put(role.getValue(), role);
        }
    }

    RoleEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回NORMAL
     * @param value 枚举值
     * @return 枚举对象
     */
    public static RoleEnum fromValue(String value) {
        return value == null ? NORMAL : VALUE_MAP.getOrDefault(value, NORMAL);
    }
}
