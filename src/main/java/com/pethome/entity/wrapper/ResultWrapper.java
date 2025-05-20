package com.pethome.entity.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 16 20:53
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper {

    private boolean succeeded;
    private Object result;
    private Object extraInfo;

    public ResultWrapper(boolean succeeded, Object result) {
        this.succeeded = succeeded;
        this.result = result;
    }
}