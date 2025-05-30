package com.pethome.controller;

import com.pethome.dto.Result;
import com.pethome.entity.enums.RoleEnum;
import com.pethome.entity.mybatis.Role;
import com.pethome.entity.mybatis.User;
import com.pethome.service.UserRoleService;
import com.pethome.service.UserService;
import com.pethome.util.ResultUtil;
import com.pethome.util.UserUtil;
import com.star.jwt.annotation.JwtAuthority;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 存放用户信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@Tag(name = "UserController", description = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService,
                          UserRoleService userRoleService) {
        Assert.notNull(userService, "userService must not be null");
        Assert.notNull(userRoleService, "userRoleService must not be null");
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @JwtAuthority(enabled = false)
    @PostMapping("/public/regist")
    public Result regist(@RequestBody User user) {
        boolean isSuccess = userService.addUser(user);
        if (!isSuccess) {
            return ResultUtil.fail_400(null, "注册失败");
        }
        return ResultUtil.success_200(null, "注册成功");
    }

    @JwtAuthority
    @PostMapping("/public/login/token")
    public Result loginToken(@RequestAttribute String userId) {
        // 能够进入这个方法，说明已通过jwt验证，可以直接返回成功
        Map<String, String> map = new HashMap<>();
        map.put("user_id",userId);
        return ResultUtil.success_200(map, "token验证成功");
    }

    @JwtAuthority(enabled = false)
    @GetMapping("/public/info/{id}")
    public Result getPublicInfo(@PathVariable("id") Integer id) {
        User user = userService.getPublicInfoById(id);
        return ResultUtil.success_200(user, "获取用户信息成功");
    }

    @JwtAuthority
    @GetMapping("/info/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        User user = UserUtil.removeProhibitedInfo(userService.getUserInfoById(id));
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("user_info", user);
        return ResultUtil.success_200(resMap, "获取用户信息成功");
    }

    @JwtAuthority
    @PutMapping("/info/{id}")
    public Result updateInfo(@PathVariable("id") Integer id, @RequestBody User user){
        if(id == null || user == null){
            return ResultUtil.fail_400(null,"参数不能为空");
        }
        boolean isSuccess = userService.updateUserInfo(id,user);
        if(!isSuccess){
            return ResultUtil.fail_400(null,"更新失败");
        }
        return ResultUtil.success_200(null,"更新成功");
    }

    @JwtAuthority
    @GetMapping("/{id}/role")
    public Result getUserRoleList(@PathVariable("id") Integer id){
        if(id == null){
            return ResultUtil.fail_400(null,"参数不能为空");
        }
        List<Role> roleList = userRoleService.getUserRoleList(id);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("role_list", roleList);
        return ResultUtil.success_200(resMap, "获取用户角色列表成功");
    }

    @JwtAuthority
    @PostMapping("/{id}/role")
    public Result updateUserRole(@PathVariable("id") Integer id, @RequestBody List<RoleEnum> roleTagList){
        if(id == null || roleTagList == null){
            return ResultUtil.fail_400(null,"参数不能为空");
        }
        userRoleService.updateUserRole(id,roleTagList);
        return ResultUtil.success_200(null, "更新用户角色成功");
    }
}