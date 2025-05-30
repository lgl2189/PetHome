package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.enums.RoleEnum;
import com.pethome.entity.mybatis.Role;
import com.pethome.entity.mybatis.UserRole;

import java.util.List;

/**
 * <p>
 * 存储用户-角色关系 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据用户ID获取角色列表，其中不包括超级管理员角色（super）
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> getUserRoleList(Integer userId);

    /**
     * 更新用户角色
     * @param userId 用户ID
     * @param roleList 新的完整角色列表
     */
    void updateUserRole(Integer userId, List<RoleEnum> roleList);
}
