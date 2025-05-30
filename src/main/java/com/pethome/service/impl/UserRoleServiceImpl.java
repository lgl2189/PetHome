package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.enums.RoleEnum;
import com.pethome.entity.mybatis.Role;
import com.pethome.entity.mybatis.UserRole;
import com.pethome.mapper.UserRoleMapper;
import com.pethome.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 存储用户-角色关系 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final UserRoleMapper userRoleMapper;
    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        Assert.notNull(userRoleMapper, "UserRoleMapper must not be null");
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<Role> getUserRoleList(Integer userId) {
        List<Role> roleList = userRoleMapper.getRoleListByUserId(userId);
        // 删除管理员权限，普通用户不应该知道该账户是否拥有管理员权限
        roleList.removeIf(role -> role.getRoleTag() == RoleEnum.SUPER);
        return roleList;
    }
}
