package com.pethome.handler.security;

import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.constant.Constant;
import com.pethome.entity.web.Result;
import com.pethome.entity.web.UserDetail;
import com.pethome.util.ResultUtil;
import com.pethome.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 30 15:25
 */

@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public UserLoginSuccessHandler(ObjectMapper objectMapper, RedisTemplate<String,Object> redisTemplate) {
        Assert.notNull(objectMapper, "ObjectMapper must not be null");
        Assert.notNull(redisTemplate, "RedisTemplate must not be null");
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //生成JWT
        UserDetail userDetail = UserUtil.removeSensitiveInfo(
                (UserDetail) authentication.getPrincipal());
        // 设置过期时间
        LocalDateTime expireDateTime = LocalDateTime.now().plusDays(1);
        userDetail.setExpireDateTime(expireDateTime);

        String userJson = objectMapper.writeValueAsString(userDetail);
        String jwt = JWTUtil.createToken(Map.of("user", userJson), Constant.JWT_SECRET_BYTE);
        // 存入Redis
        String userId = userDetail.getUserId().toString();
        String redisKey = Constant.REDIS_KEY_LOGIN_TOKEN + userId;
        redisTemplate.opsForList().rightPush(redisKey, jwt);
        Result result = ResultUtil.success_200(Map.of(Constant.REQUEST_USER_TOKEN_KEY, jwt), "登陆成功");
        String json = objectMapper.writeValueAsString(result);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}