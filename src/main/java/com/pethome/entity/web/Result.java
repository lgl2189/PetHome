package com.pethome.entity.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ：李冠良
 * @description： 无描述
 * @date ：2024/10/27 下午2:07
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Object data;
    private String status;
    private String message;

    public Result(Object data) {
        this.data = data;
    }
}