package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.AdoptionApplication;

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
     * 添加一条领养申请
     * @param adoptionApplication 领养申请
     * @return 是否添加成功
     */
    boolean addAdoptionApplication(AdoptionApplication adoptionApplication);
}
