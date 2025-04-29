package com.pethome.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pethome.entity.mybatis.Admin;
import com.pethome.mapper.AdminMapper;
import com.pethome.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储超级管理员信息 服务实现类
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}