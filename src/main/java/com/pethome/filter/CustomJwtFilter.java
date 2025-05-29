package com.pethome.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.constant.Constant;
import com.pethome.dto.Result;
import com.pethome.dto.UserDetail;
import com.pethome.util.ResultUtil;
import com.star.jwt.JwtFilter;
import com.star.jwt.JwtFilterChain;
import com.star.jwt.JwtUrlHandler;
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
    protected void jwtAuthority(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull JwtFilterChain filterChain) throws ServletException, IOException {
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
        Result result = ResultUtil.fail_403(null,"token不合法");
        String token = request.getHeader(Constant.REQUEST_USER_TOKEN_KEY);
        if (!StringUtils.hasText(token)) {
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        //验证token格式是否正确
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
        boolean isTokenExist = false;
        for (Object obj : rightTokenList) {
            String rightToken = (String) obj;
            LocalDateTime now = LocalDateTime.now();
            if (rightToken.equals(token)) {
                isTokenExist = true;
            }
            // 清理该用户过期的token
            String checkedUserJson = JWT.of(rightToken).getPayloads().get("user", String.class);
            UserDetail checkedUser = objectMapper.readValue(checkedUserJson, UserDetail.class);
            if(now.isAfter(checkedUser.getExpireDateTime())){
                redisTemplate.opsForList().remove(redisKey, 0, rightToken);
            }
        }
        if (!isTokenExist) {
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
        // 获取用户权限信息
        List<GrantedAuthority> authorityList = new ArrayList<>(user.getAuthorities());
        // token通过验证后，还需要在SpringSecurity的上下文中设置认证对象，保证在执行后续的Filter时知道该请求是通过登录验证的
        // 否则后续的Filter将误认为该请求为未登录
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user, null, authorityList);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 将userId放入request中，后续的Filter中可以直接使用
        request.setAttribute("userId", user.getUserId());
        // 执行后续的Filter
        filterChain.doFilter(request, response);
    }
}