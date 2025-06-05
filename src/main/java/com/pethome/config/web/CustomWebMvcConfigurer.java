package com.pethome.config.web;

import com.pethome.config.FileUploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 15 10:47
 */

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    private final FileUploadConfig fileUploadConfig;

    @Autowired
    public CustomWebMvcConfigurer(FileUploadConfig fileUploadConfig) {
        Assert.notNull(fileUploadConfig, "FileUploadConfig must not be null");
        this.fileUploadConfig = fileUploadConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**")
                .addResourceLocations("file:/"+fileUploadConfig.getFileUploadRootPath()+"/");
    }
}