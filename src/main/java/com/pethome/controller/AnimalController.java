package com.pethome.controller;

import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.Animal;
import com.pethome.entity.web.AnimalReceiver;
import com.pethome.entity.web.Result;
import com.pethome.service.AnimalService;
import com.pethome.util.DatabasePageUtil;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 存储动物信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        Assert.notNull(animalService, "animalService must not be null");
        this.animalService = animalService;
    }

    // 将参数处理器的 默认为非简单类型 选项设置false，再加@ModelAttribute会导致请求不使用自定义的绑定器，导致参数无法绑定到实体类中
    // 原因未知
    @JwtAuthority
    @PostMapping("/upload/info")
    public Result uploadInfo(AnimalReceiver animalReceiver) throws IOException {
        if (animalReceiver == null) {
            return ResultUtil.fail_401("提交表单为空");
        }
        animalService.saveAnimalInfo(animalReceiver);
        return ResultUtil.success_200(null, "上传成功");
    }

    @JwtAuthority
    @GetMapping("/info/recommend")
    public Result getRecommendList(@RequestParam(defaultValue = "20") int num) {
        List<Animal> animalRecommendList = animalService.getAnimalListRecommended(num);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_list", animalRecommendList);
        resMap.put("record_num", num);
        return ResultUtil.success_200(resMap, "获取成功");
    }

    @JwtAuthority
    @GetMapping("/info/search")
    public Result getSearchList(@RequestParam("key") List<String> keyList, int pageNum, int pageSize) {
        if(keyList == null || keyList.isEmpty()) {
            return ResultUtil.fail_401(null, "搜索关键字不能为空");
        }
        PageInfo<Animal> animalPageInfo = animalService.searchAnimalInfo(keyList, pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_list",animalPageInfo.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(animalPageInfo));
        return ResultUtil.success_200(resMap, "搜索成功");
    }
}
