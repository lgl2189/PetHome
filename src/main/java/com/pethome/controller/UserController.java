package com.pethome.controller;

import com.pethome.entity.mybatis.User;
import com.pethome.entity.web.Result;
import com.pethome.service.UserService;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/tokenVerify")
    @PreAuthorize("hasRole('super') or hasRole('admin') or hasRole('volunteer') " +
            "or hasRole('adopter') or hasRole('donator') or hasRole('normal')")
    public Result tokenVerify() {
        return ResultUtil.success_200(null,"token验证成功");
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody User user){
        userService.addUser(user);
        return ResultUtil.success_200(null,"注册成功");
    }

    @GetMapping("/getInfo")
    public Result getInfo(){
        return ResultUtil.success_200("获取用户信息成功");
    }
}