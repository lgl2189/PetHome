package com.pethome.config.web;

import com.pethome.constant.Constant;
import com.star.jwt.JwtUrlHandler;
import com.star.jwt.enums.JwtAuthorityStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 5月 08 20:40
 */

@Configuration
public class JwtAuthorityConfig {
    @Bean
    public JwtUrlHandler jwtUrlHandler(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        JwtUrlHandler jwtUrlHandler = new JwtUrlHandler(requestMappingHandlerMapping);
        jwtUrlHandler.setDefaultAuthorityStrategy(JwtAuthorityStrategy.ALL_AUTHORITY);
        jwtUrlHandler.addExtraIgnoreUrl(Constant.JWT_IGNORE_URL_LIST);
        return jwtUrlHandler;
    }
}