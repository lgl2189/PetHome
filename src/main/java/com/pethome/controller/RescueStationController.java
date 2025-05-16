package com.pethome.controller;

import com.pethome.entity.mybatis.RescueStation;
import com.pethome.entity.web.Result;
import com.pethome.service.RescueStationService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 存储救助站信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/rescueStation")
public class RescueStationController {

    private final RescueStationService rescueStationService;

    public RescueStationController(RescueStationService rescueStationService) {
        Assert.notNull(rescueStationService, "RescueStationService must not be null");
        this.rescueStationService = rescueStationService;
    }

    @JwtAuthority(enabled = false)
    @GetMapping("/public/info/list")
    public Result getRescueStationList(){
        List<RescueStation> rescueStationList = rescueStationService.getPublicInfoList();
        Map<String, Object> rescueStationMap = new HashMap<>();
        rescueStationMap.put("station_info", rescueStationList);
        return ResultUtil.success_200(rescueStationMap);
    }

}
