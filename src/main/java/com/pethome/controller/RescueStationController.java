package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.service.RescueStationService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 05 09:57
 */

@RestController
@RequestMapping("/station")
public class RescueStationController {

    private final RescueStationService rescueStationService;

    @Autowired
    public RescueStationController(RescueStationService rescueStationService) {
        Assert.notNull(rescueStationService, "rescueStationService must not be null");
        this.rescueStationService = rescueStationService;
    }

    @JwtAuthority(enabled = false)
    @GetMapping("/public/list")
    public Result getRescueStationList() {
        List<RescueStation> rescueStationList = rescueStationService.getPublicInfoList();
        Map<String, Object> rescueStationMap = new HashMap<>();
        rescueStationMap.put("station_info", rescueStationList);
        return ResultUtil.success_200(rescueStationMap);
    }

    @JwtAuthority
    @GetMapping("/list")
    public Result getStationListByAdminId(@RequestParam("adminId") Integer adminId) {
        if (adminId == null) {
            return ResultUtil.fail_401(null, "缺少参数");
        }
        List<RescueStation> rescueStationList = rescueStationService.getStationListByAdminId(adminId);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("station_list", rescueStationList);
        return ResultUtil.success_200(resMap);
    }
}