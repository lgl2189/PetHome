package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.Animal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 存储动物信息 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Mapper
public interface AnimalMapper extends BaseMapper<Animal> {
    /**
     * 随机获取指定数量的动物信息
     * @param limit 数量
     * @return 动物信息列表
     */
    List<Animal> selectRandomAnimalList(int limit);

    List<Animal> selectRandomAnimalListWaitAdopt(int limit);
    /**
     * 根据关键词查询动物信息列表
     * @param keyList 关键词列表，关键词列表不能为空
     * @param pageNum 分页当前页码
     * @param pageSize 分页大小
     * @return 动物信息列表
     */
    List<Animal> selectAnimalByKeyList(List<String> keyList, int pageNum, int pageSize);

    /**
     * 根据关键词查询待领养的动物信息列表
     * @param keyList 关键词列表，关键词列表不能为空
     * @param pageNum 分页当前页码
     * @param pageSize 分页大小
     * @return 待领养的动物信息列表
     */
    List<Animal> selectAnimalWaitAdoptByKeyList(List<String> keyList, int pageNum, int pageSize);
}
