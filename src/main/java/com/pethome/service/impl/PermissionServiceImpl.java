package com.pethome.service.impl;

import com.pethome.entity.Permission;
import com.pethome.mapper.PermissionMapper;
import com.pethome.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储权限信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
