package com.pethome.config.web;

import com.pethome.config.web.binder.SnakeToCamelArgumentProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 15 10:47
 */

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SnakeToCamelArgumentProcessor(true));
    }
}