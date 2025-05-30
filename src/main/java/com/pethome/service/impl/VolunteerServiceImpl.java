package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.Volunteer;
import com.pethome.mapper.VolunteerMapper;
import com.pethome.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 存储志愿者信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class VolunteerServiceImpl extends ServiceImpl<VolunteerMapper, Volunteer> implements VolunteerService {

    private final VolunteerMapper volunteerMapper;

    @Autowired
    public VolunteerServiceImpl(VolunteerMapper volunteerMapper) {
        Assert.notNull(volunteerMapper, "volunteerMapper must not be null");
        this.volunteerMapper = volunteerMapper;
    }

    @Override
    public Volunteer getVolunteerByUserId(Integer userId) {
        LambdaQueryWrapper<Volunteer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Volunteer::getUserId, userId);
        return volunteerMapper.selectOne(queryWrapper);
    }
}
