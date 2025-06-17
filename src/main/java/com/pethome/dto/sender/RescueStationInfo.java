package com.pethome.dto.sender;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.entity.mybatis.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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

    public RescueStationInfo(RescueStation rescueStation, User user) {
        this.rescueStation = rescueStation;
        this.user = user;
    }

    @JsonUnwrapped
    private RescueStation rescueStation;
    @JsonProperty("payment_qrcode_url_list")
    private List<String> paymentQrcodeUrlList;
    @JsonUnwrapped(prefix = "admin_")
    private User user;
}