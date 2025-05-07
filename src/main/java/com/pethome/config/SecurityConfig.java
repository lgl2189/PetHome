package com.pethome.config;

import com.pethome.constant.Constant;
import com.pethome.filter.JwtFilter;
import com.pethome.handler.security.UserLoginFailureHandler;
import com.pethome.handler.security.UserLoginSuccessHandler;
import com.pethome.handler.security.UserLogoutSuccessHandler;
import com.pethome.scanner.JwtIgnoreScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.util.Assert;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 28 13:27
 */

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final UserLoginSuccessHandler userLoginSuccessHandler;
    private final UserLoginFailureHandler userLoginFailureHandler;
    private final UserLogoutSuccessHandler userLogoutSuccessHandler;
    private final JwtFilter jwtFilter;
    private final JwtIgnoreScanner jwtIgnoreScanner;

    @Autowired
    public SecurityConfig(UserLoginSuccessHandler userLoginSuccessHandler,
                          UserLoginFailureHandler userLoginFailureHandler,
                          UserLogoutSuccessHandler userLogoutSuccessHandler,
                          JwtFilter jwtFilter,
                          JwtIgnoreScanner jwtIgnoreScanner) {
        Assert.notNull(userLoginSuccessHandler, "userLoginSuccessHandler cannot be null");
        Assert.notNull(userLoginFailureHandler, "userLoginFailureHandler cannot be null");
        Assert.notNull(userLogoutSuccessHandler, "userLogoutSuccessHandler cannot be null");
        Assert.notNull(jwtFilter, "jwtFilter cannot be null");
        Assert.notNull(jwtIgnoreScanner, "jwtIgnoreScanner cannot be null");
        this.userLoginSuccessHandler = userLoginSuccessHandler;
        this.userLoginFailureHandler = userLoginFailureHandler;
        this.userLogoutSuccessHandler = userLogoutSuccessHandler;
        this.jwtFilter = jwtFilter;
        this.jwtIgnoreScanner = jwtIgnoreScanner;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        List<String> ignoreUrls = jwtIgnoreScanner.getIgnoreUrlList();
        ignoreUrls.addAll(Constant.EXTRA_IGNORE_JWT_URL_LIST);
        return httpSecurity
                .addFilterBefore(characterEncodingFilter, CsrfFilter.class)
                .addFilterBefore(jwtFilter, LogoutFilter.class)
                .formLogin(formLogin -> {
                    //没有设置登录成功跳转地址，默认跳转到根路径斜杠（"/"）
                    formLogin.loginProcessingUrl(Constant.USER_LOGIN_URL)
                            .successHandler(userLoginSuccessHandler)
                            .failureHandler(userLoginFailureHandler);
                })
                .logout(logout -> {
                    logout.logoutUrl(Constant.USER_LOGOUT_URL)
                            .logoutSuccessHandler(userLogoutSuccessHandler);
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                // 这个配置用户豁免的登陆和注册对于身份的验证，但不豁免token验证，
                // 所以需要在JwtFilter中手动豁免登录和注册的请求
                .authorizeHttpRequests(requests -> {
                    requests.antMatchers(ignoreUrls.toArray(String[]::new)).permitAll();
                })
                //跨站请求伪造保护关闭
                .csrf().disable()
                //允许跨域请求访问
                .cors(cors -> {
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
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return configurationSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}