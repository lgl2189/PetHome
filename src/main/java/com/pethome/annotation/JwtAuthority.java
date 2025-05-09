package com.pethome.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：李冠良
 * @description ：用于忽略JWT验证的注解，仅可用于方法上
 * @date ：2025 5月 06 22:12
 */

/**
 * 用于忽略JWT验证的注解，仅可用于方法上。由于本注解的解析是在Spring的RequestMappingHandlerMapping中进行的，
 * 该注解仅可用于使用了@RequestMapping、@GetMapping等Spring框架规定的可以指定请求路径的注解上
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtAuthority {

    /**
     * 这是enabled的别名, 用于标注是否启用JWT验证，默认值为true
     */
    @AliasFor("enabled")
    boolean value() default true;

    /**
     * 是否启用JWT验证，默认值为true
     */
    @AliasFor("value")
    boolean enabled() default true;
}