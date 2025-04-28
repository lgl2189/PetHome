package com.pethome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pethome.entity.User;
import com.pethome.service.SecurityUserService;
import com.pethome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 28 14:34
 */


public class SecurityUserServiceImpl implements SecurityUserService {

    private UserService userService;

    @Autowired
    public SecurityUserServiceImpl(UserService userService) {
        Assert.notNull(userService, "userService must not be null");
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}