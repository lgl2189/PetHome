package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pethome.entity.mybatis.Role;
import com.pethome.entity.mybatis.User;
import com.pethome.entity.web.UserDetail;
import com.pethome.mapper.RoleMapper;
import com.pethome.mapper.UserMapper;
import com.pethome.mapper.UserRoleMapper;
import com.pethome.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 28 14:34
 */


@Service
public class SecurityUserServiceImpl implements SecurityUserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public SecurityUserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, UserRoleMapper userRoleMapper) {
        Assert.notNull(userMapper, "userService must not be null");
        Assert.notNull(userRoleMapper, "userRoleMapper must not be null");
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUserName, username);
        User user = userMapper.selectOne(query);
        if (user == null) {
            throw new UsernameNotFoundException("登录账号不存在 " + username);
        }
        //查询用户角色列表
        List<Role> roleList = userRoleMapper.getRolesByUserId(user.getUserId());
        return new UserDetail(user, roleList);
    }
}