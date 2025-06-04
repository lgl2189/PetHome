package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.AdoptionBlack;
import com.pethome.mapper.AdoptionBlackMapper;
import com.pethome.service.AdoptionBlackService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储领养黑名单 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class AdoptionBlackServiceImpl extends ServiceImpl<AdoptionBlackMapper, AdoptionBlack> implements AdoptionBlackService {

    @Override
    public AdoptionBlack getBlackUser(Integer userId) {
        return this.getById(userId);
    }

    @Override
    public List<AdoptionBlack> getBlackListByStationId(Integer stationId){
        LambdaQueryWrapper<AdoptionBlack> query = new LambdaQueryWrapper<>();
        query.eq(AdoptionBlack::getRescueStationId, stationId);
        return this.list(query);
    }

    @Override
    public boolean addBlackUser(AdoptionBlack adoptionBlack) {
        return save(adoptionBlack);
    }

    @Override
    public boolean deleteBlackUser(Integer userId) {
        return removeById(userId);
    }

    @Override
    public boolean isBlackUser(Integer userId) {
        return this.getById(userId) != null;
    }
}
