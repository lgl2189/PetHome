package com.pethome.entity.enums;

import lombok.Getter;

// 物资需求状态枚举类
@Getter
public enum SupplyDemandStatusEnum {
    INCOMPLETE("未完成"),
    COMPLETE("已完成");

    private final String value;

    SupplyDemandStatusEnum(String value) {
        this.value = value;
    }

}