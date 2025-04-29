package com.pethome.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasRole('super')")
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
