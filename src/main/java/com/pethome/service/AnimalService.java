package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pethome.entity.mybatis.Animal;
import com.pethome.entity.web.receiver.AnimalReceiver;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 存储动物信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface AnimalService extends IService<Animal> {
    /**
     * 保存动物信息
     *
     * @param animalReceiver 动物信接收器实体类
     * @throws IOException 保存文件时发生IoException
     */
    void saveAnimalInfo(AnimalReceiver animalReceiver) throws IOException;

    /**
     * 获取推荐的动物列表
     *
     * @param num 推荐数量
     * @return 推荐的动物列表
     */
    List<Animal> getAnimalListRecommended(int num);

    /**
     * 搜索动物信息
     *
     * @param keyList  搜索关键次列表
     * @param pageSize 每页数量
     * @return 搜索结果，动物信息列表
     */
    PageInfo<Animal> searchAnimalInfo(List<String> keyList, int pageNum, int pageSize);

    /**
     * 根据动物id获取动物信息
     * @param animalId 动物id
     * @return Animal类型封装的动物信息
     */
    Animal getAnimalInfoById(int animalId);
}
