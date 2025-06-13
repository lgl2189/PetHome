package com.pethome.controller;

import com.github.pagehelper.PageInfo;
import com.pethome.config.FileUploadConfig;
import com.pethome.dto.Result;
import com.pethome.dto.receiver.AnimalReceiver;
import com.pethome.dto.sender.AnimalSender;
import com.pethome.dto.sender.RescueStationInfo;
import com.pethome.entity.mybatis.Animal;
import com.pethome.entity.mybatis.RescueRecord;
import com.pethome.service.AnimalService;
import com.pethome.service.FileRecordService;
import com.pethome.service.RescueRecordService;
import com.pethome.service.RescueStationService;
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
    private final RescueStationService rescueStationService;
    private final RescueRecordService rescueRecordService;

    @Autowired
    public AnimalController(FileUploadConfig fileUploadConfig,
                            AnimalService animalService,
                            FileRecordService fileRecordService,
                            RescueStationService rescueStationService,
                            RescueRecordService rescueRecordService) {
        Assert.notNull(fileUploadConfig, "fileUploadConfig must not be null");
        Assert.notNull(animalService, "animalService must not be null");
        Assert.notNull(fileRecordService, "fileRecordService must not be null");
        Assert.notNull(rescueStationService, "rescueStationService must not be null");
        Assert.notNull(rescueRecordService, "rescueRecordService must not be null");
        this.fileUploadConfig = fileUploadConfig;
        this.animalService = animalService;
        this.fileRecordService = fileRecordService;
        this.rescueStationService = rescueStationService;
        this.rescueRecordService = rescueRecordService;
    }

    @JwtAuthority
    @PostMapping("/upload/info")
    public Result uploadInfo(AnimalReceiver animalReceiver) throws IOException {
        if (animalReceiver == null) {
            return ResultUtil.fail_401("提交表单为空");
        }
        Animal animalInfo = animalService.saveAnimalInfo(animalReceiver);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_info", animalInfo);
        return ResultUtil.success_200(resMap, "上传成功");
    }

    @JwtAuthority
    @GetMapping("/info/recommend")
    public Result getRecommendList(@RequestParam(defaultValue = "20") int num) {
        List<Animal> animalRecommendList = animalService.getAnimalListRecommended(num);
        List<AnimalSender> animalSenderList = new ArrayList<>();
        for (Animal animal : animalRecommendList) {
            AnimalSender animalSender = getFileUrl(animal);
            animalSenderList.add(animalSender);
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
            AnimalSender animalSender = getFileUrl(animal);
            animalSenderList.add(animalSender);
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_list", animalSenderList);
        resMap.put("page_info", DatabasePageUtil.getPageInfo(animalPageInfo));
        return ResultUtil.success_200(resMap, "搜索成功");
    }

    @JwtAuthority
    @GetMapping("/list/wait-adopt/recommend")
    public Result getWaitAdoptRecommendList(@RequestParam(defaultValue = "20") int num) {
        List<Animal> animalRecommendList = animalService.getAnimalListWaitAdoptRecommended(num);
        List<AnimalSender> animalSenderList = new ArrayList<>();
        for (Animal animal : animalRecommendList) {
            AnimalSender animalSender = getFileUrl(animal);
            animalSenderList.add(animalSender);
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("animal_list", animalSenderList);
        resMap.put("record_num", num);
        return ResultUtil.success_200(resMap, "获取成功");
    }

    @JwtAuthority
    @GetMapping("/list/wait-adopt")
    public Result getWaitAdoptedList(@RequestParam("key") List<String> keyList, int pageNum, int pageSize) {
        if (keyList == null || keyList.isEmpty()) {
            return ResultUtil.fail_401(null, "搜索关键字不能为空");
        }
        PageInfo<Animal> animalPageInfo = animalService.searchAnimalInfoWaitAdopt(keyList, pageNum, pageSize);
        List<AnimalSender> animalSenderList = new ArrayList<>();
        for (Animal animal : animalPageInfo.getList()) {
            AnimalSender animalSender = getFileUrl(animal);
            animalSenderList.add(animalSender);
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
        Map<String, Object> resMap = getCompleteAnimalSender(animalInfo);
        return ResultUtil.success_200(resMap, "获取成功");
    }

    private Map<String, Object> getCompleteAnimalSender(Animal animal){
        Map<String, Object> resMap = new HashMap<>();
        AnimalSender animalSender = getFileUrl(animal);
        RescueStationInfo rescueStationInfo = rescueStationService.getRescueStationById(animal.getRescueStationId());
        RescueRecord rescueRecord = rescueRecordService.getRescueRecordByAnimalId(animal.getAnimalId());
        resMap.put("animal_info", animalSender);
        resMap.put("rescue_station_info", rescueStationInfo);
        resMap.put("rescue_record", rescueRecord);
        return resMap;
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
