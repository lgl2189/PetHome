package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.RescueStation;
import com.pethome.entity.mybatis.User;
import com.pethome.entity.web.sender.RescueStationInfo;
import com.pethome.mapper.RescueStationMapper;
import com.pethome.service.RescueStationService;
import com.pethome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public final UserService userService;

    @Autowired
    public RescueStationServiceImpl(UserService userService) {
        Assert.notNull(userService, "userService must not be null");
        this.userService = userService;
    }

    @Override
    public List<RescueStation> getPublicInfoList() {
        LambdaQueryWrapper<RescueStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(RescueStation::getRescueStationId,RescueStation::getRescueStationName);
        return list(queryWrapper);
    }

    @Override
    public RescueStationInfo getRescueStationById(Integer rescueStationId) {
        RescueStation rescueStation = getById(rescueStationId);
        User user = userService.getPublicInfoById(rescueStation.getAdminUserId());
        return new RescueStationInfo(rescueStation,user);
    }
}