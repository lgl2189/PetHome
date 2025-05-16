package com.pethome.entity.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// 领养申请状态枚举类
@Getter
public enum AdoptionApplicationStatusEnum {
    PENDING_REVIEW("待审核"),
    UNDER_REVIEW("审核中"),
    APPROVED("审核通过");

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

    /**
     * 从枚举值获取枚举对象，当value为null时返回PENDING_REVIEW
     * @param value 枚举值
     * @return 枚举对象
     */
    public static AdoptionApplicationStatusEnum fromValue(String value) {
        return value == null ? PENDING_REVIEW : VALUE_MAP.getOrDefault(value, PENDING_REVIEW);
    }


}