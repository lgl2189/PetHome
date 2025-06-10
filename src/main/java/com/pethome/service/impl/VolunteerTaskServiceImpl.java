package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.enums.VolunteerTaskStatusEnum;
import com.pethome.entity.mybatis.VolunteerTask;
import com.pethome.mapper.VolunteerTaskMapper;
import com.pethome.service.VolunteerTaskService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

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
    public PageInfo<VolunteerTask> getVolunteerTaskRecordListAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list());
    }

    @Override
    public PageInfo<VolunteerTask> getVolunteerTaskRecordListByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<VolunteerTask> query = new LambdaQueryWrapper<>();
        query.like(VolunteerTask::getTaskPosition, "%" + keyword + "%");
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list(query));
    }

    @Override
    public PageInfo<VolunteerTask> getVolunteerListByStationId(Integer id, int pageNum, int pageSize) {
        List<VolunteerTask> taskList;
        try {
            PageHelper.startPage(pageNum, pageSize);
            LambdaQueryWrapper<VolunteerTask> query = new LambdaQueryWrapper<>();
            query.eq(VolunteerTask::getRescueStationId, id);
            taskList = this.list(query);
        }
        finally {
            PageHelper.clearPage();
        }
        return new PageInfo<>(taskList);
    }

    @Override
    public boolean addVolunteerTask(VolunteerTask volunteerTask) {
        volunteerTask.setTaskStatus(VolunteerTaskStatusEnum.AVAILABLE);
        LocalTime durationTime = volunteerTask.getTaskDuration();
        int point = durationTime.getHour() * 60 + durationTime.getMinute();
        volunteerTask.setTaskPoint(point);
        return this.save(volunteerTask);
    }

    @Override
    public boolean updateVolunteerTask(VolunteerTask volunteerTask) {
        return this.updateById(volunteerTask);
    }
}
