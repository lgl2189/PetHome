package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.Volunteer;

/**
 * <p>
 * 存储志愿者信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface VolunteerService extends IService<Volunteer> {

    /**
     * 根据用户id获取志愿者信息
     * @param userId 用户id
     * @return 志愿者信息
     */
    Volunteer getVolunteerByUserId(Integer userId);
}
