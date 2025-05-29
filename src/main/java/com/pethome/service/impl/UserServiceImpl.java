package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.constant.Constant;
import com.pethome.entity.mybatis.User;
import com.pethome.entity.mybatis.UserRole;
import com.pethome.mapper.UserMapper;
import com.pethome.service.UserRoleService;
import com.pethome.service.UserService;
import com.pethome.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 存放用户信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    private final UserMapper userMapper;
    private final UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,UserRoleService userRoleService) {
        Assert.notNull(userMapper, "userMapper must not be null");
        Assert.notNull(userRoleService, "userRoleService must not be null");
        this.userMapper = userMapper;
        this.userRoleService = userRoleService;
    }

    @Override
    public boolean addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getUserPassword());
        user.setUserPassword(encodedPassword);
        boolean isUserInsert = this.save(user);
        if(!isUserInsert){
            return false;
        }
        Integer userId = user.getUserId();
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(Constant.ROLE_NORMAL_ID);
        return userRoleService.save(userRole);
    }

    @Override
    public User getPublicInfoById(Integer id) {
        User user = userMapper.selectById(id);
        user = UserUtil.removeSensitiveInfo(user);
        return user;
    }

    @Override
    public User getUserInfoById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean updateUserInfo(int id, User user) {
        User newUser = UserUtil.getModifiableUser(user);
        newUser.setUserId(id);
        int effectRow = userMapper.updateById(newUser);
        return effectRow == 1;
    }
}
