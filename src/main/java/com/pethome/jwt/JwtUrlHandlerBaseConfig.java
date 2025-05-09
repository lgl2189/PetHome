package com.pethome.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author ：李冠良
 * @description ：    用于提供JwtUrlHandler的默认对象
 * @date ：2025 5月 08 20:31
 */

@Configuration
public class JwtUrlHandlerBaseConfig {

    /**
     * 如果容器中没有JwtUrlHandler类型的Bean，则创建一个新的JwtUrlHandler Bean
     * @param requestMappingHandlerMapping Spring MVC的RequestMappingHandlerMapping实例
     * @return JwtUrlHandler实例
     */
    @Bean
    @ConditionalOnMissingBean(JwtUrlHandler.class)
    public JwtUrlHandler jwtUrlHandler(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        return new JwtUrlHandler(requestMappingHandlerMapping);
    }

}