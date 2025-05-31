package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.Role;

import java.util.List;

/**
 * <p>
 * 存储角色信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
public interface RoleService extends IService<Role> {
    /**
     * 获取用户的角色列表
     * @return 角色列表
     */
    List<Role> getUserRoleList();
}
