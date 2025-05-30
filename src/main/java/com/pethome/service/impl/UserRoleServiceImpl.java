package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.enums.RoleEnum;
import com.pethome.entity.mybatis.Role;
import com.pethome.entity.mybatis.UserRole;
import com.pethome.mapper.RoleMapper;
import com.pethome.mapper.UserRoleMapper;
import com.pethome.service.RoleService;
import com.pethome.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleService roleService;

    @Autowired
    public UserRoleServiceImpl(RoleMapper roleMapper, UserRoleMapper userRoleMapper, RoleService roleService) {
        Assert.notNull(roleMapper, "RoleMapper must not be null");
        Assert.notNull(userRoleMapper, "UserRoleMapper must not be null");
        Assert.notNull(roleService, "RoleService must not be null");
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleService = roleService;
    }

    @Override
    public List<Role> getUserRoleList(Integer userId) {
        List<Role> roleList = userRoleMapper.getRoleListByUserId(userId);
        // 删除管理员权限，普通用户不应该知道该账户是否拥有管理员权限
        roleList.removeIf(role -> role.getRoleTag() == RoleEnum.SUPER);
        return roleList;
    }

    @Override
    public void updateUserRole(Integer userId, List<RoleEnum> roleTagList) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        userRoleMapper.delete(wrapper);
        Map<RoleEnum, Integer> roleIdMap = roleService.list().stream().collect(Collectors.toMap(
                Role::getRoleTag,
                Role::getRoleId,
                (existing, replacement) -> existing// 冲突策略：保留先出现的对象
        ));
        List<UserRole> roleList = roleTagList.stream()
                .map(roleEnum -> {
                    return new UserRole(userId, roleIdMap.get(roleEnum));
                })
                .collect(Collectors.toList());
        userRoleMapper.insert(roleList);
    }
}
