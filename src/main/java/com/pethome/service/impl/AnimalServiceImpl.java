package com.pethome.service.impl;

import com.pethome.entity.mybatis.Animal;
import com.pethome.mapper.AnimalMapper;
import com.pethome.service.AnimalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储动物信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements AnimalService {

}
