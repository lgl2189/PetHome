package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.Animal;
import com.pethome.entity.web.AnimalReceiver;

import java.io.IOException;

/**
 * <p>
 * 存储动物信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface AnimalService extends IService<Animal> {
    void saveAnimalInfo(AnimalReceiver animalReceiver) throws IOException;
}
