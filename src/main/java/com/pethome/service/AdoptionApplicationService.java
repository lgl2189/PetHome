package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.AdoptionApplication;

import java.util.List;

/**
 * <p>
 * 存储领养信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface AdoptionApplicationService extends IService<AdoptionApplication> {

    /**
     * 根据领养id查询领养申请
     * @param adoptionId 领养id
     * @return 领养申请
     */
    AdoptionApplication getAdoptionApplicationByAdoptionId(Integer adoptionId);

    /**
     * 根据救助站id查询领养申请列表
     * @param stationId 救助站id
     * @return 领养申请列表
     */
    List<AdoptionApplication> getApplicationListByStationId(Integer stationId);
    /**
     * 添加一条领养申请
     * @param adoptionApplication 领养申请
     * @return 是否添加成功
     */
    boolean addAdoptionApplication(AdoptionApplication adoptionApplication);

    /**
     * 更新领养申请的审核状态
     * @param applicationId 待更新的领养申请id
     * @param status 待更新的审核状态
     * @return 是否更新成功
     */
    boolean updateApplicationStatus(Integer applicationId, String status);
}
