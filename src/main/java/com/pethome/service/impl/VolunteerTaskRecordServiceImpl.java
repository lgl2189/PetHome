package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.VolunteerTaskRecord;
import com.pethome.mapper.VolunteerTaskRecordMapper;
import com.pethome.service.VolunteerTaskRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储志愿者任务记录 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-06-04
 */
@Service
public class VolunteerTaskRecordServiceImpl extends ServiceImpl<VolunteerTaskRecordMapper, VolunteerTaskRecord> implements VolunteerTaskRecordService {

    @Override
    public PageInfo<VolunteerTaskRecord> getVolunteerTaskRecordByStationId(Integer stationId, Integer pageNum, Integer pageSize) {
        List<VolunteerTaskRecord> recordList;
        try {
            PageHelper.startPage(pageNum, pageSize);
            LambdaQueryWrapper<VolunteerTaskRecord> query = new LambdaQueryWrapper<>();
            query.eq(VolunteerTaskRecord::getRescueStationId, stationId);
            recordList = this.list(query);
        }
        finally{
            PageHelper.clearPage();
        }
        return new PageInfo<>(recordList);
    }

    @Override
    public PageInfo<VolunteerTaskRecord> getVolunteerTaskRecordByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<VolunteerTaskRecord> query = new LambdaQueryWrapper<>();
        query.eq(VolunteerTaskRecord::getUserId, userId);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.list(query));
    }

    @Override
    public boolean updateVolunteerTaskRecord(VolunteerTaskRecord volunteerTaskRecord) {
        return this.updateById(volunteerTaskRecord);
    }
}
