package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.service.RescueRecordService;
import com.pethome.service.RescueStationService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 存储救助相关内容 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/rescue")
public class RescueController {
    private final RescueStationService rescueStationService;
    private final RescueRecordService rescueRecordService;

    @Autowired
    public RescueController(RescueStationService rescueStationService, RescueRecordService rescueRecordService) {
        Assert.notNull(rescueStationService, "rescueStationService must not be null");
        Assert.notNull(rescueRecordService, "rescueRecordService must not be null");
        this.rescueStationService = rescueStationService;
        this.rescueRecordService = rescueRecordService;
    }

    @JwtAuthority(enabled = false)
    @GetMapping("/station/public/info/list")
    public Result getRescueStationList(){
        List<RescueStation> rescueStationList = rescueStationService.getPublicInfoList();
        Map<String, Object> rescueStationMap = new HashMap<>();
        rescueStationMap.put("station_info", rescueStationList);
        return ResultUtil.success_200(rescueStationMap);
    }


}
