package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.AdoptionApplication;
import com.pethome.mapper.AdoptionApplicationMapper;
import com.pethome.service.AdoptionApplicationService;
import org.springframework.stereotype.Service;

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

}
