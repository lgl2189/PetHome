package com.pethome.scanner;

import com.pethome.annotation.JwtIgnore;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：李冠良
 * @description ：用于扫描@JwtIgnore注解的类，并将其加入忽略扫描列表中，其扫描结果只能在所有bean初始化完成后才能获取到，
 * 因为无法确定Spring中Controller的扫描顺序，所以不能在其它Bean的初始化流程中获取到该Bean的扫描结果。
 * 不要在任何Bean的初始化流程中获取到该Bean的扫描结果，否则可能会导致扫描到的列表错误。
 * @date ：2025 5月 06 22:21
 */

@Component
public class JwtIgnoreScanner implements BeanPostProcessor {

    private final List<String> ignoreUrlList = new ArrayList<>();

    /**
     * 返回忽略扫描列表的拷贝，避免外部修改该列表
     * @return 忽略扫描列表的拷贝
     */
    public List<String> getIgnoreUrlList() {
        return new ArrayList<>(ignoreUrlList);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        // 判断类是否是Controller类
        List<String> urlList = new ArrayList<>();
        if (AnnotationUtils.findAnnotation(targetClass, Controller.class) != null) {
            // 判断Controller类是否有RequestMapping注解，如果有则获取其value数组
            String[] clazzUrls = null;
            RequestMapping clazzRequestMapping = AnnotationUtils.findAnnotation(targetClass, RequestMapping.class);
            if (clazzRequestMapping != null) {
                clazzUrls = clazzRequestMapping.value();
            }
            // 遍历Controller类的方法
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(targetClass);
            for (Method method : methods) {
                // 判断方法是否有@JwtIgnore注解，如果有则处理该方法
                JwtIgnore jwtIgnore = AnnotationUtils.findAnnotation(method, JwtIgnore.class);
                if (jwtIgnore != null) {
                    // 判断方法是否有@RequestMapping一系列注解，如果有则获取其value数组，存储在methodUrls中
                    String[] methodUrls = getMethodUrls(method);
                    // 将Controller类和方法的RequestMapping注解的value数组合并到urlList中
                    // 如果有clazzUrls，则将其与methodUrls连接，添加到urlList中
                    // 否则直接将methodUrls添加到urlList中
                    if (methodUrls != null) {
                        if (clazzUrls != null) {
                            for (String clazzUrl : clazzUrls) {
                                for (String methodUrl : methodUrls) {
                                    String url = clazzUrl + methodUrl;
                                    if(!urlList.contains(url)){
                                        urlList.add(url);
                                    }
                                }
                            }
                        }
                        else {
                            urlList.addAll(Arrays.asList(methodUrls));
                        }
                    }
                }
            }
        }
        // 将urlList加入忽略扫描列表中
        ignoreUrlList.addAll(urlList);
        return bean;
    }

    private String[] getMethodUrls(Method method) {
        // 依次尝试获取不同类型的请求映射注解
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            return requestMapping.value();
        }
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        if (getMapping != null && getMapping.value().length > 0) {
            return getMapping.value();
        }
        PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
        if (postMapping != null && postMapping.value().length > 0) {
            return postMapping.value();
        }
        PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
        if (putMapping != null && putMapping.value().length > 0) {
            return putMapping.value();
        }
        DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        if (deleteMapping != null && deleteMapping.value().length > 0) {
            return deleteMapping.value();
        }
        PatchMapping patchMapping = AnnotationUtils.findAnnotation(method, PatchMapping.class);
        if (patchMapping != null && patchMapping.value().length > 0) {
            return patchMapping.value();
        }
        return null;
    }

}