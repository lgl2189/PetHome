package com.pethome.dto.sender;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pethome.entity.mybatis.User;
import com.pethome.entity.mybatis.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 30 15:35
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerSender implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private User user;
    private Volunteer volunteer;
}