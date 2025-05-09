package com.pethome.controller;

import com.pethome.entity.mybatis.User;
import com.pethome.entity.web.Result;
import com.pethome.jwt.annotation.JwtAuthority;
import com.pethome.service.UserService;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 存放用户信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
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
    public Result loginToken() {
        // 能够进入这个方法，说明已通过jwt验证，可以直接返回成功
        return ResultUtil.success_200(null, "token验证成功");
    }

    @JwtAuthority(enabled = false)
    @GetMapping("/public/getInfo/{id}")
    public Result getInfo(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return ResultUtil.success_200(user, "获取用户信息成功");
    }
}