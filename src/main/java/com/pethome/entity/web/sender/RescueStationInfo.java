package com.pethome.entity.web.sender;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.entity.mybatis.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 26 17:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueStationInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private RescueStation rescueStation;
    @JsonUnwrapped(prefix = "admin_")
    private User user;
}