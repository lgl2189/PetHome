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

    /**
     * 添加用户
     * @param user 待添加的用户对象
     * @return true 成功 false 失败
     */
    boolean addUser(User user);

    /**
     * 获取用户公开信息
     * @param id 待获取信息的用户id
     * @return 用户信息对象
     */
    User getPublicInfoById(Integer id);

    /**
     * 获取用户全部信息
     * @param id 待获取信息的用户id
     * @return 用户信息对象
     */
    User getUserInfoById(Integer id);

    /**
     * 更新用户信息
     *
     * @param id 待更新的用户id
     * @param user 待更新的用户对象
     * @return true 成功 false 失败
     */
    boolean updateUserInfo(int id,User user);

    /**
     * 根据用户id获取用户名
     * @param userId 用户id
     * @return 用户名
     */
    String getUserName(Integer userId);
}