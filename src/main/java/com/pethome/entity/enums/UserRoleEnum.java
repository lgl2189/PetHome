package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum UserRoleEnum {
    ADMIN("admin"),
    VOLUNTEER("volunteer"),
    ADOPTER("adopter"),
    DONATOR("donator"),
    NORMAL("normal");

    @EnumValue
    private final String value;
    private static final Map<String, UserRoleEnum> VALUE_MAP = new HashMap<>();

    static {
        for (UserRoleEnum role : UserRoleEnum.values()) {
            VALUE_MAP.put(role.getValue(), role);
        }
    }

    UserRoleEnum(String value) {
        this.value = value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回NORMAL
     * @param value 枚举值
     * @return 枚举对象
     */
    public static UserRoleEnum fromValue(String value) {
        return value == null ? NORMAL : VALUE_MAP.getOrDefault(value, NORMAL);
    }
}
