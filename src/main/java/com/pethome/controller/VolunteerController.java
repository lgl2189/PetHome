package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.mybatis.VolunteerTask;
import com.pethome.service.VolunteerService;
import com.pethome.service.VolunteerTaskRecordService;
import com.pethome.service.VolunteerTaskService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final VolunteerService volunteerService;
    private final VolunteerTaskService volunteerTaskService;
    private final VolunteerTaskRecordService volunteerTaskRecordService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService,
                               VolunteerTaskService volunteerTaskService,
                               VolunteerTaskRecordService volunteerTaskRecordService) {
        Assert.notNull(volunteerService, "volunteerService must not be null");
        Assert.notNull(volunteerTaskService, "volunteerTaskService must not be null");
        Assert.notNull(volunteerTaskRecordService, "volunteerTaskRecordService must not be null");
        this.volunteerService = volunteerService;
        this.volunteerTaskService = volunteerTaskService;
        this.volunteerTaskRecordService = volunteerTaskRecordService;
    }

    @JwtAuthority
    @PostMapping("/task")
    public Result addTask(VolunteerTask volunteerTask) {
        if(volunteerTask == null){
            return ResultUtil.fail_401(null,"任务数据参数为空");
        }
        boolean result = volunteerTaskService.addVolunteerTask(volunteerTask);
        if(!result){
            return ResultUtil.fail_500(null,"任务数据保存失败");
        }
        return ResultUtil.success_200(null,"任务数据保存成功");
    }
}
