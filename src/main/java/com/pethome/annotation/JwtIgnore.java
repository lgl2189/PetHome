package com.pethome.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：李冠良
 * @description ：用于忽略JWT验证的注解，仅可用于方法上
 * @date ：2025 5月 06 22:12
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtIgnore {
}