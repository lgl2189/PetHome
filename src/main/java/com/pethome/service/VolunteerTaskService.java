package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.VolunteerTask;

/**
 * <p>
 * 存储志愿者任务 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface VolunteerTaskService extends IService<VolunteerTask> {
    /**
     * 获取所有志愿者任务列表
     * @param pageNum 当前页码
     * @param pageSize 页大小
     * @return 志愿者任务列表
     */
    PageInfo<VolunteerTask> getVolunteerTaskRecordListAll(Integer pageNum, Integer pageSize);

    /**
     * 根据关键字，获取所有志愿者任务列表
     *
     * @param keyword  关键字
     * @param pageNum  当前页码
     * @param pageSize 页大小
     * @return 志愿者任务列表
     */
    PageInfo<VolunteerTask> getVolunteerTaskRecordListByKeyword(String keyword,Integer pageNum, Integer pageSize);
    /**
     * 根据站点id获取志愿者任务列表
     *
     * @param id 站点id
     * @return 志愿者任务列表
     */
    PageInfo<VolunteerTask> getVolunteerListByStationId(Integer id,int pageNum,int pageSize);
    /**
     * 添加志愿者任务
     * @param volunteerTask 志愿者任务
     * @return 成功返回true，失败返回false
     */
    boolean addVolunteerTask(VolunteerTask volunteerTask);

    /**
     * 更新志愿者任务
     * @param volunteerTask 新的志愿者任务对象
     * @return 成功返回true，失败返回false
     */
    boolean updateVolunteerTask(VolunteerTask volunteerTask);
}
