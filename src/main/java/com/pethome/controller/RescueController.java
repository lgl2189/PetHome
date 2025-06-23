package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.mybatis.Animal;
import com.pethome.entity.mybatis.RescueRecord;
import com.pethome.service.AnimalService;
import com.pethome.service.RescueRecordService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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

    private final AnimalService animalService;
    private final RescueRecordService rescueRecordService;

    @Autowired
    public RescueController(AnimalService animalService,RescueRecordService rescueRecordService) {
        Assert.notNull(animalService, "animalService must not be null");
        Assert.notNull(rescueRecordService, "rescueRecordService must not be null");
        this.animalService = animalService;
        this.rescueRecordService = rescueRecordService;
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
        return ResultUtil.success_200(rescueRecord, "救助信息获取成功");
    }

    @JwtAuthority
    @PostMapping("/record")
    public Result addRescueRecord(@RequestBody RescueRecord rescueRecord) {
        if (rescueRecord == null) {
            return ResultUtil.fail_401(null, "提交参数为空");
        }
        RescueRecord record = rescueRecordService.addRescueRecord(rescueRecord);
        if (record == null) {
            return ResultUtil.fail_500(null, "救助信息提交失败");
        }
        Animal animal = new Animal();
        animal.setAnimalId(record.getAnimalId());
        animal.setRescueStationId(record.getRescueStationId());
        animalService.updateById(animal);
        return ResultUtil.success_200(null, "救助信息提交成功");
    }

    @JwtAuthority
    @PutMapping("/record/{id}")
    public Result updateRescueRecordStatus(@RequestAttribute Integer userId, @PathVariable("id") Integer rescueId, @RequestBody RescueRecord rescueRecord) {
        if (rescueId == null || rescueRecord == null) {
            return ResultUtil.fail_401(null, "缺少参数");
        }
        if (!rescueId.equals(rescueRecord.getRescueId())) {
            return ResultUtil.fail_402(null, "参数不匹配");
        }
        RescueRecord rescueRecordByRescueId = rescueRecordService.getRescueRecordByRescueId(rescueId);
        if (!rescueRecordByRescueId.getRescuerId().equals(userId)) {
            return ResultUtil.fail_403(null, "无权限修改");
        }
        rescueRecord.setRescuerId(null);
        boolean result = rescueRecordService.updateRescueRecord(rescueRecord);
        if (!result) {
            return ResultUtil.fail_500(null, "修改救助记录状态失败");
        }
        return ResultUtil.success_200(null, "修改救助记录状态成功");
    }
}
