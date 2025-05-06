package com.pethome.controller;

import com.pethome.entity.mybatis.User;
import com.pethome.entity.web.Result;
import com.pethome.util.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/tokenVerify")
    @PreAuthorize("hasRole('super') or hasRole('admin') or hasRole('volunteer') " +
            "or hasRole('adopter') or hasRole('donator') or hasRole('normal')")
    public Result tokenVerify() {
        return ResultUtil.success_200(null,"token验证成功");
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody User user){
        System.out.println(user);
        return ResultUtil.success_200(null,"注册成功");
    }
}