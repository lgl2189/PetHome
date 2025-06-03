package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.dto.sender.RescueStationInfo;
import com.pethome.entity.mybatis.RescueStation;

import java.util.List;

/**
 * <p>
 * 存储救助站信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface RescueStationService extends IService<RescueStation> {
    /**
     * 获取所有公开的救助站信息
     * @return 公开的救助站信息列表
     */
    List<RescueStation> getPublicInfoList();

    /**
     * 根据管理员id获取该管理员管理的救助站的信息
     * @param adminId 管理员id
     * @return 该管理员管理的救助站信息列表
     */
    List<RescueStation> getStationListByAdminId(Integer adminId);
    /**
     * 根据救助站id获取救助站信息
     * @param rescueStationId 救助站id
     * @return 救助站信息
     */
    RescueStationInfo getRescueStationById(Integer rescueStationId);
}
