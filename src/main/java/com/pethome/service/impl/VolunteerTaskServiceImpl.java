package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.enums.VolunteerTaskStatusEnum;
import com.pethome.entity.mybatis.VolunteerTask;
import com.pethome.mapper.VolunteerTaskMapper;
import com.pethome.service.VolunteerTaskService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * <p>
 * 存储志愿者任务 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class VolunteerTaskServiceImpl extends ServiceImpl<VolunteerTaskMapper, VolunteerTask> implements VolunteerTaskService {

    @Override
    public boolean addVolunteerTask(VolunteerTask volunteerTask) {
        volunteerTask.setTaskStatus(VolunteerTaskStatusEnum.AVAILABLE);
        LocalTime durationTime = volunteerTask.getTaskDuration();
        int point = durationTime.getHour() * 60 + durationTime.getMinute();
        volunteerTask.setTaskPoint(point);
        return this.save(volunteerTask);
    }
}
