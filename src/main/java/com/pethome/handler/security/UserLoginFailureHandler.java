package com.pethome.handler.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.dto.Result;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 30 15:25
 */

@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Autowired
    public UserLoginFailureHandler(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper must not be null");
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result result = ResultUtil.fail_402(null,"登陆失败");
        String json = objectMapper.writeValueAsString(result);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}