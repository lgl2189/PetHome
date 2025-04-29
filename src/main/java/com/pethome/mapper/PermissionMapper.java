package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储权限信息 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
