package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * 添加志愿者任务
     * @param volunteerTask 志愿者任务
     * @return 成功返回true，失败返回false
     */
    boolean addVolunteerTask(VolunteerTask volunteerTask);
}
