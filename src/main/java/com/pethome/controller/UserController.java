package com.pethome.controller;

import com.pethome.entity.mybatis.User;
import com.pethome.entity.web.Result;
import com.pethome.service.UserService;
import com.pethome.util.ResultUtil;
import com.star.jwt.annotation.JwtAuthority;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Autowired
    public UserController(UserService userService) {
        Assert.notNull(userService, "userService must not be null");
        this.userService = userService;
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

    @PostMapping("/public/login/token")
    public Result loginToken(@RequestAttribute String userId) {
        // 能够进入这个方法，说明已通过jwt验证，可以直接返回成功
        Map<String, String> map = new HashMap<>();
        map.put("user_id",userId);
        return ResultUtil.success_200(map, "token验证成功");
    }

    @JwtAuthority(enabled = false)
    @GetMapping("/public/info/{id}")
    public Result getInfo(@PathVariable("id") Integer id) {
        User user = userService.getPublicInfoById(id);
        return ResultUtil.success_200(user, "获取用户信息成功");
    }
}