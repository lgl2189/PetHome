package com.pethome.entity.enums;

import lombok.Getter;

// 领养申请状态枚举类
@Getter
public enum AdoptionApplicationStatusEnum {
    PENDING_REVIEW("待审核"),
    UNDER_REVIEW("审核中"),
    APPROVED("审核通过");

    private final String value;

    AdoptionApplicationStatusEnum(String value) {
        this.value = value;
    }

}