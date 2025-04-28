package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.Animal;
import org.apache.ibatis.annotations.Mapper;

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

}
