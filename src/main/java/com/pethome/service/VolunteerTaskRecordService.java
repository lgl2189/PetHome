package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.VolunteerTaskRecord;

import java.util.List;

/**
 * <p>
 * 存储志愿者任务记录 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-06-04
 */
public interface VolunteerTaskRecordService extends IService<VolunteerTaskRecord> {
    /**
     * 根据志愿者id获取志愿者任务记录
     *
     * @param stationId 站点id
     * @param pageNum 页码
     * @param pageSize 一页包含记录的数量
     * @return 志愿者任务记录列表
     */
    PageInfo<VolunteerTaskRecord> getVolunteerTaskRecordByStationId(Integer stationId, Integer pageNum, Integer pageSize);

    /**
     * 根据志愿者id更新志愿者任务记录
     * @param volunteerTaskRecord 志愿者任务记录
     * @return 是否更新成功
     */
    boolean updateVolunteerTaskRecord(VolunteerTaskRecord volunteerTaskRecord);
}
