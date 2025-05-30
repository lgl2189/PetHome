package com.pethome.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 领养申请状态枚举类
@Getter
public enum AdoptionApplicationStatusEnum {
    PENDING_REVIEW("pending_review"),
    REVIEWING("reviewing"),
    APPROVED("approved");

    @EnumValue
    private final String value;
    private static final Map<String, AdoptionApplicationStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (AdoptionApplicationStatusEnum status : AdoptionApplicationStatusEnum.values()) {
            VALUE_MAP.put(status.getValue(), status);
        }
    }

    AdoptionApplicationStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * 从枚举值获取枚举对象，当value为null时返回PENDING_REVIEW
     * @param value 枚举值
     * @return 枚举对象
     */
    public static AdoptionApplicationStatusEnum fromValue(String value) {
        return value == null ? PENDING_REVIEW : VALUE_MAP.getOrDefault(value, PENDING_REVIEW);
    }


}