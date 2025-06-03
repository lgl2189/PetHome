package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.enums.AdoptionApplicationStatusEnum;
import com.pethome.entity.mybatis.AdoptionApplication;
import com.pethome.mapper.AdoptionApplicationMapper;
import com.pethome.service.AdoptionApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储领养信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class AdoptionApplicationServiceImpl extends ServiceImpl<AdoptionApplicationMapper, AdoptionApplication> implements AdoptionApplicationService {

    @Override
    public AdoptionApplication getAdoptionApplicationByAdoptionId(Integer adoptionId) {
        return this.getById(adoptionId);
    }

    @Override
    public List<AdoptionApplication> getApplicationListByStationId(Integer stationId) {
        LambdaQueryWrapper<AdoptionApplication> query = new LambdaQueryWrapper<>();
        query.eq(AdoptionApplication::getRescueStationId, stationId);
        return this.list(query);
    }

    @Override
    public boolean addAdoptionApplication(AdoptionApplication adoptionApplication) {
        return this.save(adoptionApplication);
    }

    @Override
    public boolean updateApplicationStatus(Integer applicationId, String status) {
        AdoptionApplicationStatusEnum statusEnum = AdoptionApplicationStatusEnum.fromValue(status);
        LambdaUpdateWrapper<AdoptionApplication> update = new LambdaUpdateWrapper<>();
        update.eq(AdoptionApplication::getAdoptionId, applicationId);
        update.set(AdoptionApplication::getApplicationStatus, statusEnum);
        return this.update(update);
    }
}
