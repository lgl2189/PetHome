package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.VolunteerTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储志愿者任务 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Mapper
public interface VolunteerTaskMapper extends BaseMapper<VolunteerTask> {

}
