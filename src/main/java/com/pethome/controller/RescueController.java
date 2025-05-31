package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.mybatis.RescueRecord;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.service.RescueRecordService;
import com.pethome.service.RescueStationService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
    public Result getRescueStationList() {
        List<RescueStation> rescueStationList = rescueStationService.getPublicInfoList();
        Map<String, Object> rescueStationMap = new HashMap<>();
        rescueStationMap.put("station_info", rescueStationList);
        return ResultUtil.success_200(rescueStationMap);
    }

    @JwtAuthority
    @GetMapping("/record/{id}")
    public Result getRescueRecord(@PathVariable("id") Integer rescueId) {
        if (rescueId == null) {
            return ResultUtil.fail_401(null, "缺少参数");
        }
        RescueRecord rescueRecord = rescueRecordService.getRescueRecordByRescueId(rescueId);
        if (rescueRecord == null) {
            return ResultUtil.fail_404(null, "救助信息不存在");
        }
        return ResultUtil.success_200(rescueRecord,"救助信息获取成功");
    }

    @JwtAuthority
    @PostMapping("/record")
    public Result addRescueRecord(@RequestBody RescueRecord rescueRecord) {
        if (rescueRecord == null) {
            return ResultUtil.fail_401(null, "提交参数为空");
        }
        RescueRecord result = rescueRecordService.addRescueRecord(rescueRecord);
        if (result == null) {
            return ResultUtil.fail_500(null, "救助信息提交失败");
        }
        return ResultUtil.success_200(null, "救助信息提交成功");
    }

    @JwtAuthority
    @PutMapping("/record/{id}")
    public Result updateRescueRecordStatus(@PathVariable("id") Integer rescueId,@RequestBody RescueRecord rescueRecord) {
        if(rescueId == null || rescueRecord == null) {
            return ResultUtil.fail_401(null,"缺少参数");
        }
        if(!rescueId.equals(rescueRecord.getRescueId())){
            return ResultUtil.fail_402(null,"参数不匹配");
        }
        boolean result = rescueRecordService.updateRescueRecord(rescueRecord);
        if(!result){
            return ResultUtil.fail_500(null,"修改救助记录状态失败");
        }
        return ResultUtil.success_200(null,"修改救助记录状态成功");
    }
}
