package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.User;
import com.pethome.mapper.UserMapper;
import com.pethome.service.UserService;
import org.springframework.stereotype.Service;

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

}
