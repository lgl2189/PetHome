package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.mybatis.Role;
import com.pethome.service.RoleService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 存储角色信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-29
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        Assert.notNull(roleService, "roleService must not be null");
        this.roleService = roleService;
    }

    @JwtAuthority
    @GetMapping("/list")
    public Result getRoleList(){
        List<Role> roleList = roleService.getUserRoleList();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("role_list", roleList);
        return ResultUtil.success_200(resMap,"获取角色列表成功");
    }

}
