package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 存储角色-权限关系 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
