package com.pethome.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.constant.Constant;
import com.pethome.entity.web.Result;
import com.pethome.entity.web.UserDetail;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 5月 02 19:07
 */

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static final List<String> ignoreJwtUrlList = Constant.IGNORE_JWT_URL_LIST;

    @Autowired
    public JwtFilter(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper,
                     RequestMappingHandlerMapping requestMappingHandlerMapping) {
        Assert.notNull(redisTemplate, "redisTemplate must not be null");
        Assert.notNull(objectMapper, "objectMapper must not be null");
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //排除登录接口，不验证token
        String requestUri = request.getRequestURI();
        for(String ignoreJwtUrl : ignoreJwtUrlList){
            if(requestUri.startsWith(ignoreJwtUrl)){
                filterChain.doFilter(request, response);
                return;
            }
        }
        response.setContentType("application/json");
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            Result result = ResultUtil.fail_401("token为空");
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        //验证token格式是否正确
        boolean verifyValue;
        try {
            verifyValue = JWTUtil.verify(token, Constant.JWT_SECRET_BYTE);
        }
        catch (Exception e) {
            Result result = ResultUtil.fail_402("token不合法");
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        if (!verifyValue) {
            Result result = ResultUtil.fail_402("token不合法");
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        //验证token是否正确
        UserDetail user;
        Result result = ResultUtil.fail_402("token不合法");
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
        String RightToken = (String) redisTemplate.opsForHash().get(Constant.REDIS_TOKEN_KEY, user.getUserId().toString());
        if (RightToken == null) {
            response.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        if (!RightToken.equals(token)) {
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
    }
}