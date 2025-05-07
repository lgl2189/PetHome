package com.pethome.handler.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.constant.Constant;
import com.pethome.entity.web.Result;
import com.pethome.entity.web.UserDetail;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 5月 01 17:37
 */

@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserLogoutSuccessHandler(ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate) {
        Assert.notNull(objectMapper, "objectMapper cannot be null");
        Assert.notNull(redisTemplate, "redisTemplate cannot be null");
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        // 清除redis中的token
        if (authentication == null) {
            Result result = ResultUtil.fail_402("参数错误，用户未登录");
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        if (userDetail == null || userDetail.getUserId() == null) {
            Result result = ResultUtil.fail_402("参数错误，用户未登录");
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        redisTemplate.opsForHash().delete(Constant.REDIS_KEY_LOGIN_TOKEN, userDetail.getUserId().toString());
        Result result = ResultUtil.success_200(null, "登出成功");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}