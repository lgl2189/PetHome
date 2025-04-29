package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.UserRole;
import com.pethome.mapper.UserRoleMapper;
import com.pethome.service.UserRoleService;
import org.springframework.stereotype.Service;

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

}
