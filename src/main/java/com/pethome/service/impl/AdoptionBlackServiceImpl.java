package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.AdoptionBlack;
import com.pethome.mapper.AdoptionBlackMapper;
import com.pethome.service.AdoptionBlackService;
import org.springframework.stereotype.Service;

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
    public boolean isBlackUser(Integer userId) {
        return this.getById(userId) != null;
    }
}
