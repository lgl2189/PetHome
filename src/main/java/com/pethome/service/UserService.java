package com.pethome.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pethome.entity.mybatis.User;

/**
 * <p>
 * 存放用户信息 服务类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
public interface UserService extends IService<User>{

    boolean addUser(User user);

    User getUserById(Integer id);

}