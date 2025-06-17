package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.dto.sender.RescueStationInfo;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.service.RescueStationService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @JwtAuthority
    @GetMapping("/{stationId}")
    public Result getStationInfoById(@PathVariable("stationId") Integer stationId) {
        if(stationId == null) {
            return ResultUtil.fail_401(null, "缺少参数");
        }
        RescueStationInfo rescueStationInfo = rescueStationService.getRescueStationById(stationId);
        if(rescueStationInfo == null) {
            return ResultUtil.fail_404(null, "未找到该站点信息");
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("station_info", rescueStationInfo);
        return ResultUtil.success_200(resMap,"获取成功");
    }

    @JwtAuthority
    @PutMapping("/{stationId}/qrcode")
    public Result updateQrcode(@PathVariable("stationId") Integer stationId, @RequestPart("qrcode_list") MultipartFile[] qrcodeFileArray) throws IOException {
        if (stationId == null || qrcodeFileArray == null || qrcodeFileArray.length == 0) {
            return ResultUtil.fail_401(null, "缺少参数");
        }
        boolean result = rescueStationService.updatePaymentQrcodeList(stationId, qrcodeFileArray);
        if(!result){
            return ResultUtil.fail_500(null, "更新二维码列表失败");
        }
        return ResultUtil.success_200(null,"更新二维码列表成功");
    }
}