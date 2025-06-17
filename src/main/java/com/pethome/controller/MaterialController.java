package com.pethome.controller;

import com.github.pagehelper.PageInfo;
import com.pethome.dto.Result;
import com.pethome.entity.mybatis.Inventory;
import com.pethome.entity.mybatis.SupplyDemandRecord;
import com.pethome.service.InventoryService;
import com.pethome.service.SupplyDemandRecordService;
import com.pethome.util.DatabasePageUtil;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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

    private final InventoryService inventoryService;
    private final SupplyDemandRecordService supplyDemandRecordService;

    @Autowired
    public MaterialController(InventoryService inventoryService,
                              SupplyDemandRecordService supplyDemandRecordService) {
        Assert.notNull(inventoryService,"inventoryService must not be null");
        Assert.notNull(supplyDemandRecordService, "supplyDemandRecordService must not be null");
        this.inventoryService = inventoryService;
        this.supplyDemandRecordService = supplyDemandRecordService;
    }

    @JwtAuthority
    @GetMapping("/demand/list/all")
    public Result getAllDemandList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 10;
        PageInfo<SupplyDemandRecord> demandListPageInfo = supplyDemandRecordService.getAllSupplyDemandRecord(pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("demand_list", demandListPageInfo.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(demandListPageInfo));
        return ResultUtil.success_200(resMap, "需求列表获取成功");
    }

    @JwtAuthority
    @GetMapping("/inventory/list/station/{stationId}")
    public Result getInventoryListByStation(@PathVariable Integer stationId,
                                              @RequestParam Integer pageNum,
                                              @RequestParam Integer pageSize) {
        if (stationId == null) return ResultUtil.fail_401("缺少stationId参数");
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 10;
        PageInfo<Inventory> inventoryPageInfo = inventoryService.getInventoryByStation(stationId, pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("inventory_list",inventoryPageInfo.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(inventoryPageInfo));
        return ResultUtil.success_200(resMap, "需求列表获取成功");
    }

    @JwtAuthority
    @PostMapping("/inventory")
    public Result addInventory(@RequestBody Inventory inventory) {
        if(inventory == null){
            return ResultUtil.fail_401("缺少参数");
        }
        boolean result = inventoryService.save(inventory);
        if (!result) {
            return ResultUtil.fail_500("新增库存失败");
        }
        return ResultUtil.success_200(null,"库存添加成功");
    }
}
