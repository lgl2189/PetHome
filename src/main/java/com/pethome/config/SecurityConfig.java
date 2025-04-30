package com.pethome.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethome.handler.security.UserLoginFailureHandler;
import com.pethome.handler.security.UserLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.Assert;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 28 13:27
 */

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    @Autowired
    public SecurityConfig(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper must not be null");
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(formLogin -> {
                    //没有设置登录成功跳转地址，默认跳转到根路径斜杠（"/"）
                    formLogin.loginProcessingUrl("/user/login")
                            .successHandler(new UserLoginSuccessHandler(objectMapper))
                            .failureHandler(new UserLoginFailureHandler(objectMapper));
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.anyRequest().authenticated();
                })
                //跨站请求伪造保护关闭
                .csrf().disable()
                //允许跨域请求访问
                .cors(cors->{
                    cors.configurationSource(corsConfigurationSource());
                })
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configurationSource = new UrlBasedCorsConfigurationSource();
        var corsConfiguration = new CorsConfiguration();
        // 允许任何来源，允许任何方法（Get、Post等），允许任何请求头（JWT）
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return configurationSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}