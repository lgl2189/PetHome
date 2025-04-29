package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.Role;
import com.pethome.mapper.RoleMapper;
import com.pethome.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储角色信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
