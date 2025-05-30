package com.pethome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pethome.entity.mybatis.Role;
import com.pethome.entity.mybatis.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 存储用户-角色关系 Mapper 接口
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Role> getRoleListByUserId(Integer userId);
}
