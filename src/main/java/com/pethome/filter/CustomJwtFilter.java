package com.pethome.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.constant.Constant;
import com.pethome.entity.web.Result;
import com.pethome.entity.web.UserDetail;
import com.pethome.jwt.JwtFilter;
import com.pethome.jwt.JwtUrlHandler;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.el.MethodNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：李冠良
 * @description ：用于验证token的Filter
 * @date ：2025 5月 02 19:07
 */

@Component
public class CustomJwtFilter extends JwtFilter {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final JwtUrlHandler jwtUrlHandler;

    @Autowired
    public CustomJwtFilter(RedisTemplate<String, Object> redisTemplate,
                           ObjectMapper objectMapper,
                           JwtUrlHandler jwtUrlHandler) {
        Assert.notNull(redisTemplate, "redisTemplate must not be null");
        Assert.notNull(objectMapper, "objectMapper must not be null");
        Assert.notNull(jwtUrlHandler, "jwtUrlHandler must not be null");
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.jwtUrlHandler = jwtUrlHandler;
    }

    @Override
    protected void jwtAuthority(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 检查请求是否需要jwt验证
        try {
            if (!jwtUrlHandler.matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        catch (MethodNotFoundException e) {
            System.out.println("请求路径在RequestMapping中不存在，忽略JWT验证: \n" + e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }
        response.setContentType("application/json");
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            Result result = ResultUtil.fail_401("token为空");
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        //验证token格式是否正确
        Result result = ResultUtil.fail_402("token不合法");
        boolean verifyValue;
        try {
            verifyValue = JWTUtil.verify(token, Constant.JWT_SECRET_BYTE);
        }
        catch (Exception e) {
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        if (!verifyValue) {
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        //验证token是否正确
        UserDetail user;
        try {
            String userJson = JWT.of(token).getPayloads().get("user", String.class);
            user = objectMapper.readValue(userJson, UserDetail.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        if (user == null || user.getUserId() == null) {
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        String redisKey = Constant.REDIS_KEY_LOGIN_TOKEN + user.getUserId();
        List<Object> rightTokenList = redisTemplate.opsForList().range(redisKey, 0, -1);
        if (rightTokenList == null) {
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        for (Object obj : rightTokenList) {
            String rightToken = (String) obj;
            if (rightToken.equals(token)) {
                LocalDateTime now = LocalDateTime.now();
                // token过期
                if (now.isAfter(user.getExpireDateTime())) {
                    //TODO: 完成token过期的处理，从Redis中删除过期Token
                    response.getWriter().write(objectMapper.writeValueAsString(result));
                    return;
                }
                // 获取用户权限信息
                List<GrantedAuthority> authorityList = new ArrayList<>(user.getAuthorities());
                // token通过验证后，还需要在SpringSecurity的上下文中设置认证对象，保证在执行后续的Filter时知道该请求是通过登录验证的
                // 否则后续的Filter将误认为该请求为未登录
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(user, null, authorityList);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // 执行后续的Filter
                filterChain.doFilter(request, response);
                return;
            }
        }
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}