package com.pethome.controller;

import com.github.pagehelper.PageInfo;
import com.pethome.config.FileUploadConfig;
import com.pethome.entity.mybatis.Animal;
import com.pethome.entity.web.AnimalReceiver;
import com.pethome.entity.web.AnimalSender;
import com.pethome.entity.web.Result;
import com.pethome.service.AnimalService;
import com.pethome.service.FileRecordService;
import com.pethome.util.DatabasePageUtil;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final FileUploadConfig fileUploadConfig;
    private final AnimalService animalService;
    private final FileRecordService fileRecordService;

    @Autowired
    public AnimalController(FileUploadConfig fileUploadConfig,
                            AnimalService animalService,
                            FileRecordService fileRecordService) {
        Assert.notNull(fileUploadConfig, "fileUploadConfig must not be null");
        Assert.notNull(animalService, "animalService must not be null");
        Assert.notNull(fileRecordService, "fileRecordService must not be null");
        this.fileUploadConfig = fileUploadConfig;
        this.animalService = animalService;
        this.fileRecordService = fileRecordService;
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
        List<AnimalSender> animalSenderList = new ArrayList<>();
        for (Animal animal : animalRecommendList) {
            animalSenderList.add(getFileUrl(animal));
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_list", animalSenderList);
        resMap.put("record_num", num);
        return ResultUtil.success_200(resMap, "获取成功");
    }

    @JwtAuthority
    @GetMapping("/info/search")
    public Result getSearchList(@RequestParam("key") List<String> keyList, int pageNum, int pageSize) {
        if (keyList == null || keyList.isEmpty()) {
            return ResultUtil.fail_401(null, "搜索关键字不能为空");
        }
        PageInfo<Animal> animalPageInfo = animalService.searchAnimalInfo(keyList, pageNum, pageSize);
        List<AnimalSender> animalSenderList = new ArrayList<>();
        for (Animal animal : animalPageInfo.getList()) {
            animalSenderList.add(getFileUrl(animal));
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_list", animalSenderList);
        resMap.put("page_info", DatabasePageUtil.getPageInfo(animalPageInfo));
        return ResultUtil.success_200(resMap, "搜索成功");
    }

    @JwtAuthority
    @GetMapping("/info/{id}")
    public Result getAnimalInfo(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResultUtil.fail_401(null, "动物ID不能为空");
        }
        Animal animalInfo = animalService.getAnimalInfoById(id);
        if (animalInfo == null) {
            return ResultUtil.fail_402(null, "动物不存在");
        }
        AnimalSender animalSender = getFileUrl(animalInfo);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_info", animalSender);
        return ResultUtil.success_200(resMap, "获取成功");
    }

    private AnimalSender getFileUrl(Animal animal) {
        AnimalSender animalSender = new AnimalSender(animal);
        if (animal.getImgGid() != null && animal.getImgGid() > 0) {
            animalSender.setImgUrlList(
                    fileRecordService.getFileRecordByFileGroupId(animal.getImgGid())
                            .stream().map(fileRecord -> fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl())
                            .collect(Collectors.toList()));
        }
        if (animal.getVideoGid() != null && animal.getVideoGid() > 0) {
            animalSender.setVideoUrlList(
                    fileRecordService.getFileRecordByFileGroupId(animal.getVideoGid())
                            .stream().map(fileRecord -> fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl())
                            .collect(Collectors.toList())
            );
        }
        if (animal.getVaccinationRecordGid() != null && animal.getVaccinationRecordGid() > 0) {
            animalSender.setVaccinationRecordUrlList(
                    fileRecordService.getFileRecordByFileGroupId(animal.getVaccinationRecordGid())
                            .stream().map(fileRecord -> fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl())
                            .collect(Collectors.toList())
            );
        }
        if (animal.getDewormingRecordGid() != null && animal.getDewormingRecordGid() > 0) {
            animalSender.setDewormingRecordUrlList(
                    fileRecordService.getFileRecordByFileGroupId(animal.getDewormingRecordGid())
                            .stream().map(fileRecord -> fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl())
                            .collect(Collectors.toList())
            );
        }
        if (animal.getMedicalReportGid() != null && animal.getMedicalReportGid() > 0) {
            animalSender.setMedicalReportUrlList(
                    fileRecordService.getFileRecordByFileGroupId(animal.getMedicalReportGid())
                            .stream().map(fileRecord -> fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl())
                            .collect(Collectors.toList())
            );
        }
        return animalSender;
    }
}
