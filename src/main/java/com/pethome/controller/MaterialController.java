package com.pethome.controller;

import com.github.pagehelper.PageInfo;
import com.pethome.dto.Result;
import com.pethome.entity.mybatis.SupplyDemandRecord;
import com.pethome.service.SupplyDemandRecordService;
import com.pethome.util.DatabasePageUtil;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 存储救助站库存 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    private final SupplyDemandRecordService supplyDemandRecordService;

    @Autowired
    public MaterialController(SupplyDemandRecordService supplyDemandRecordService) {
        Assert.notNull(supplyDemandRecordService, "supplyDemandRecordService must not be null");
        this.supplyDemandRecordService = supplyDemandRecordService;
    }

    @JwtAuthority
    @GetMapping("/demand/list/all")
    public Result getAllDemandList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        if(pageNum == null) pageNum = 1;
        if(pageSize == null) pageSize = 10;
        PageInfo<SupplyDemandRecord> demandListPageInfo = supplyDemandRecordService.getAllSupplyDemandRecord(pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("demand_list", demandListPageInfo.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(demandListPageInfo));
        return ResultUtil.success_200(resMap, "需求列表获取成功");
    }
}
