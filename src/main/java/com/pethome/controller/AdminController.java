package com.pethome.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 存储超级管理员信息 前端控制器
 * </p>
 *
 * @author lgl
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/admin")
@Tag(name = "admin",description = "超级管理员")
public class AdminController {

}
