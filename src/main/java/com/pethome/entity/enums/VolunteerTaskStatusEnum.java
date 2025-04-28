package com.pethome.entity.enums;

import lombok.Getter;

// 志愿者任务状态枚举类
@Getter
public enum VolunteerTaskStatusEnum {
    AVAILABLE("待接取"),
    ASSIGNED("已接取"),
    REVIEWING("完成审核中"),
    COMPLETED("已完成");

    private final String value;

    VolunteerTaskStatusEnum(String value) {
        this.value = value;
    }

}