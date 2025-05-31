package com.pethome.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ：李冠良
 * @description ：无描述
 * @date ：2025 4月 30 16:17
 */

@Configuration
public class JacksonConfig {

    private static final JavaTimeModule javaTimeModule = new JavaTimeModule();
    static {
        // 注册 Java 8 时间模块（处理 LocalDateTime 等新时间类型）
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 开启属性为驼峰式命名的支持
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        objectMapper.registerModule(javaTimeModule);
        // 处理旧版 Date 类型（可选）
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        // 使用配置好的 ObjectMapper
        ObjectMapper objectMapper = objectMapper();
        // 设置蛇形命名策略
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.registerModule(javaTimeModule);
        // 处理旧版 Date 类型（可选）
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}