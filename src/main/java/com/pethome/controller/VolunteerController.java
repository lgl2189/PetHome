package com.pethome.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.pethome.config.FileUploadConfig;
import com.pethome.dto.Result;
import com.pethome.dto.sender.VolunteerTaskRecordSender;
import com.pethome.entity.mybatis.VolunteerTask;
import com.pethome.entity.mybatis.VolunteerTaskRecord;
import com.pethome.service.FileRecordService;
import com.pethome.service.VolunteerTaskRecordService;
import com.pethome.service.VolunteerTaskService;
import com.pethome.util.DatabasePageUtil;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 存储志愿者信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    private final VolunteerTaskService volunteerTaskService;
    private final VolunteerTaskRecordService volunteerTaskRecordService;
    private final FileUploadConfig fileUploadConfig;
    private final FileRecordService fileRecordService;

    @Autowired
    public VolunteerController(
            VolunteerTaskService volunteerTaskService,
            VolunteerTaskRecordService volunteerTaskRecordService,
            FileUploadConfig fileUploadConfig,
            FileRecordService fileRecordService) {
        Assert.notNull(volunteerTaskService, "volunteerTaskService must not be null");
        Assert.notNull(volunteerTaskRecordService, "volunteerTaskRecordService must not be null");
        Assert.notNull(fileUploadConfig, "fileRecordService must not be null");
        Assert.notNull(fileRecordService, "fileRecordService must not be null");
        this.volunteerTaskService = volunteerTaskService;
        this.volunteerTaskRecordService = volunteerTaskRecordService;
        this.fileUploadConfig = fileUploadConfig;
        this.fileRecordService = fileRecordService;
    }

    @JwtAuthority
    @GetMapping("/task/station/{rescueStationId}")
    public Result getTaskByRescueStationId(@PathVariable("rescueStationId") Integer rescueStationId, int pageNum, int pageSize) {
        if (rescueStationId == null) {
            return ResultUtil.fail_401(null, "任务数据参数为空");
        }
        PageInfo<VolunteerTask> volunteerPageInfo = volunteerTaskService.getVolunteerListByStationId(rescueStationId, pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("task_list", volunteerPageInfo.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(volunteerPageInfo));
        return ResultUtil.success_200(resMap, "任务数据获取成功");
    }

    @JwtAuthority
    @PostMapping("/task")
    public Result addTask(@RequestBody VolunteerTask volunteerTask) {
        if (volunteerTask == null) {
            return ResultUtil.fail_401(null, "任务数据参数为空");
        }
        boolean result = volunteerTaskService.addVolunteerTask(volunteerTask);
        if (!result) {
            return ResultUtil.fail_500(null, "任务数据保存失败");
        }
        return ResultUtil.success_200(null, "任务数据保存成功");
    }

    @JwtAuthority
    @PutMapping("/task/{taskId}")
    public Result updateTask(@PathVariable("taskId") Integer taskId, @RequestBody VolunteerTask volunteerTask) {
        if (taskId == null || volunteerTask == null) {
            return ResultUtil.fail_401(null, "任务数据参数为空");
        }
        volunteerTask.setTaskId(taskId);
        boolean result = volunteerTaskService.updateVolunteerTask(volunteerTask);
        if (!result) {
            return ResultUtil.fail_500(null, "任务数据更新失败");
        }
        return ResultUtil.success_200(null, "任务数据更新成功");
    }

    @JwtAuthority
    @GetMapping("/task/all")
    public Result getTaskRecordAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return ResultUtil.fail_401(null, "任务记录数据参数为空");
        }
        PageInfo<VolunteerTask> taskList = volunteerTaskService.getVolunteerTaskRecordListAll(pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("task_list", taskList.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(taskList));
        return ResultUtil.success_200(resMap, "任务记录数据获取成功");
    }

    @JwtAuthority
    @GetMapping("/task/search")
    public Result getTaskRecordByKeyword(@RequestParam("keyword") String keyword, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        if (keyword == null || pageNum == null || pageSize == null) {
            return ResultUtil.fail_401(null, "任务记录数据参数为空");
        }
        PageInfo<VolunteerTask> taskList = volunteerTaskService.getVolunteerTaskRecordListByKeyword(keyword, pageNum, pageSize);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("task_list", taskList.getList());
        resMap.put("page_info", DatabasePageUtil.getPageInfo(taskList));
        return ResultUtil.success_200(resMap, "任务记录数据获取成功");
    }

    @JwtAuthority
    @GetMapping("/task/record/station/{rescueStationId}")
    public Result getTaskRecordByRescueStationId(@PathVariable("rescueStationId") Integer rescueStationId, int pageNum, int pageSize) {
        if (rescueStationId == null) {
            return ResultUtil.fail_401(null, "任务记录数据参数为空");
        }
        PageInfo<VolunteerTaskRecord> volunteerTaskRecordPageInfo = volunteerTaskRecordService.getVolunteerTaskRecordByStationId(rescueStationId, pageNum, pageSize);
        List<VolunteerTaskRecordSender> volunteerTaskRecordSenderList = new ArrayList<>();
        for (VolunteerTaskRecord volunteerTaskRecord : volunteerTaskRecordPageInfo.getList()) {
            VolunteerTaskRecordSender volunteerTaskRecordSender = new VolunteerTaskRecordSender();
            volunteerTaskRecordSender.setVolunteerTaskRecord(volunteerTaskRecord);
            if(volunteerTaskRecord.getTaskProveGid() != null){
                volunteerTaskRecordSender.setTaskProveList(fileRecordService.getFileRecordByFileGroupId(volunteerTaskRecord.getTaskProveGid()));
                volunteerTaskRecordSender.getTaskProveList().forEach(fileRecord -> fileRecord.setFileUrl(fileUploadConfig.getServerResourceBaseUrl() + fileRecord.getFileUrl()));
            }
            volunteerTaskRecordSenderList.add(volunteerTaskRecordSender);
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("record_list", volunteerTaskRecordSenderList);
        resMap.put("page_info", DatabasePageUtil.getPageInfo(volunteerTaskRecordPageInfo));
        return ResultUtil.success_200(resMap, "任务记录数据获取成功");
    }

    @JwtAuthority
    @PostMapping("/task/record/{taskId}")
    public Result addTaskRecord(@PathVariable("taskId") Integer taskId,@RequestParam("userId") Integer userId) {
        VolunteerTask taskInfo = volunteerTaskService.getById(taskId);
        if (taskInfo == null) {
            return ResultUtil.fail_401(null, "任务数据不存在");
        }
        VolunteerTaskRecord volunteerTaskRecord = new VolunteerTaskRecord(volunteerTaskService.getById(taskId),userId);
        LambdaQueryWrapper<VolunteerTaskRecord> query = new LambdaQueryWrapper<>();
        query.eq(VolunteerTaskRecord::getTaskId, taskId);
        query.eq(VolunteerTaskRecord::getUserId, userId);
        VolunteerTaskRecord existRecord = volunteerTaskRecordService.getOne(query);
        if (existRecord != null) {
            return ResultUtil.fail_402(null, "任务记录数据已存在");
        }
        boolean result = volunteerTaskRecordService.save(volunteerTaskRecord);
        if (!result) {
            return ResultUtil.fail_500(null, "任务记录数据保存失败");
        }
        return ResultUtil.success_200(null, "任务记录数据保存成功");
    }

    @JwtAuthority
    @PutMapping("/task/record/{recordId}")
    public Result updateTaskRecord(@PathVariable("recordId") Integer recordId, @RequestBody VolunteerTaskRecord volunteerTaskRecord) {
        if (recordId == null || volunteerTaskRecord == null) {
            return ResultUtil.fail_401(null, "任务记录数据参数为空");
        }
        volunteerTaskRecord.setRecordId(recordId);
        boolean result = volunteerTaskRecordService.updateVolunteerTaskRecord(volunteerTaskRecord);
        if (!result) {
            return ResultUtil.fail_500(null, "任务记录数据更新失败");
        }
        return ResultUtil.success_200(null, "任务记录数据更新成功");
    }
}