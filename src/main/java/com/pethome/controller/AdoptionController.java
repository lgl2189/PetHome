package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.enums.AdoptionApplicationStatusEnum;
import com.pethome.entity.mybatis.AdoptionApplication;
import com.pethome.entity.mybatis.AdoptionBlack;
import com.pethome.entity.mybatis.Animal;
import com.pethome.service.AdoptionApplicationService;
import com.pethome.service.AdoptionBlackService;
import com.pethome.service.AnimalService;
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
 * 存储领养信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    private final AnimalService animalService;
    private final AdoptionBlackService adoptionBlackService;
    private final AdoptionApplicationService adoptionApplicationService;

    @Autowired
    public AdoptionController(AdoptionApplicationService adoptionApplicationService,
                              AnimalService animalService,
                              AdoptionBlackService adoptionBlackService) {
        Assert.notNull(animalService, "animalService must not be null");
        Assert.notNull(animalService, "adoptionBlackService must not be null");
        Assert.notNull(adoptionApplicationService, "adoptionApplicationService must not be null");
        this.animalService = animalService;
        this.adoptionBlackService = adoptionBlackService;
        this.adoptionApplicationService = adoptionApplicationService;
    }

    @JwtAuthority
    @GetMapping("/application/{id}")
    public Result getAdoptionApplicationByUsername(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResultUtil.fail_400(null, "请传入正确的id");
        }
        AdoptionApplication adoptionApplication = adoptionApplicationService.getAdoptionApplicationByAdoptionId(id);
        if (adoptionApplication == null) {
            return ResultUtil.fail_404(null, "未找到该申请信息");
        }
        return ResultUtil.success_200(adoptionApplication, "查询成功");
    }

    @JwtAuthority
    @GetMapping("/application/all")
    public Result getAllAdoptionApplication() {
        List<AdoptionApplication> adoptionApplicationList = adoptionApplicationService.list();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("application_list", adoptionApplicationList);
        return ResultUtil.success_200(resMap, "查询成功");
    }

    @JwtAuthority
    @GetMapping("/application/list/{rescueStationId}")
    public Result getAdoptionApplicationList(@PathVariable("rescueStationId") Integer rescueStationId) {
        if (rescueStationId == null) {
            return ResultUtil.fail_400(null, "请传入正确的rescueStationId");
        }
        List<AdoptionApplication> adoptionApplicationList = adoptionApplicationService.getApplicationListByStationId(rescueStationId);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("application_list", adoptionApplicationList);
        return ResultUtil.success_200(resMap, "查询成功");
    }

    @JwtAuthority
    @PostMapping("/application")
    public Result addAdoptionApplication(@RequestBody AdoptionApplication adoptionApplication) {
        if (adoptionApplication == null) {
            return ResultUtil.fail_401(null, "申请信息不能为空");
        }
        adoptionApplication.setApplicationStatus(AdoptionApplicationStatusEnum.PENDING_REVIEW);
        // 判断该用户是否为黑名单用户
        if (adoptionBlackService.isBlackUser(adoptionApplication.getAdoptorId())) {
            return ResultUtil.fail_402(null, "您已被列入领养黑名单，禁止申请领养");
        }
        Animal animal = animalService.getAnimalInfoById(adoptionApplication.getAnimalId());
        if (animal == null) {
            return ResultUtil.fail_404(null, "未找到该动物信息");
        }
        adoptionApplication.setRescueStationId(animal.getRescueStationId());
        boolean result = adoptionApplicationService.addAdoptionApplication(adoptionApplication);
        if (!result) {
            return ResultUtil.fail_500(null, "申请提交失败，请稍后再试");
        }
        return ResultUtil.success_200(null, "申请成功，请等待管理员审核");
    }

    @JwtAuthority
    @PutMapping("/application/{id}/status")
    public Result updateAdoptionApplicationStatus(@PathVariable("id") Integer id,
                                                  @RequestBody Map<String, String> reqMap) {
        String statusValue = reqMap.get("application_status");
        if (id == null || statusValue == null) {
            return ResultUtil.fail_401(null, "请传入正确的id和status");
        }
        boolean result = adoptionApplicationService.updateApplicationStatus(id, statusValue);
        if (!result) {
            return ResultUtil.fail_500(null, "申请状态更新失败，申请id不存在");
        }
        return ResultUtil.success_200(null, "申请状态更新成功");
    }

    @JwtAuthority
    @GetMapping("/black/list/station/{rescueStationId}")
    public Result getBlackListByRescueStationId(@PathVariable("rescueStationId") Integer rescueStationId) {
        if (rescueStationId == null) {
            return ResultUtil.fail_400(null, "请传入正确的rescueStationId");
        }
        List<AdoptionBlack> adoptionBlackList = adoptionBlackService.getBlackListByStationId(rescueStationId);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("black_list", adoptionBlackList);
        return ResultUtil.success_200(resMap, "查询成功");
    }

    @JwtAuthority
    @PostMapping("/black")
    public Result addAdoptionBlack(@RequestBody AdoptionBlack adoptionBlack) {
        if (adoptionBlack == null) {
            return ResultUtil.fail_401(null, "黑名单信息不能为空");
        }
        boolean result = adoptionBlackService.addBlackUser(adoptionBlack);
        if (!result) {
            return ResultUtil.fail_500(null, "黑名单添加失败，请稍后再试");
        }
        return ResultUtil.success_200(null, "黑名单添加成功");
    }

    @JwtAuthority
    @DeleteMapping("/black/{userId}")
    public Result deleteAdoptionBlack(@PathVariable("userId") Integer userId) {
        if(userId == null) {
            return ResultUtil.fail_401(null, "userId不能为空");
        }
        boolean result = adoptionBlackService.deleteBlackUser(userId);
        if (!result) {
            return ResultUtil.fail_500(null, "黑名单删除失败，请稍后再试");
        }
        return ResultUtil.success_200(null,"黑名单删除成功");
    }

}
