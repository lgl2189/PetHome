package com.pethome.handler.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.entity.web.Result;
import com.pethome.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ObjectMapper objectMapper;

    @Autowired
    public UserLogoutSuccessHandler(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "objectMapper cannot be null");
        this.objectMapper = objectMapper;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result result = ResultUtil.success_200(null,"登出成功");
        String json = objectMapper.writeValueAsString(result);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}