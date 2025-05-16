package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.mapper.RescueStationMapper;
import com.pethome.service.RescueStationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储救助站信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class RescueStationServiceImpl extends ServiceImpl<RescueStationMapper, RescueStation> implements RescueStationService {

    @Override
    public List<RescueStation> getPublicInfoList() {
        LambdaQueryWrapper<RescueStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(RescueStation::getRescueStationId,RescueStation::getRescueStationName);
        return list(queryWrapper);
    }
}
