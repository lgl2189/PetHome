package com.pethome.jwt;

import com.pethome.annotation.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.el.MethodNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：李冠良
 * @description ：用于扫描@JwtIgnore注解的类，并将其加入忽略扫描列表中，其扫描结果只能在所有bean初始化完成后才能获取到，
 * 因为无法确定Spring中Controller的扫描顺序，所以不能在其它Bean的初始化流程中获取到该Bean的扫描结果。
 * 不要在任何Bean的初始化流程中获取到该Bean的扫描结果，否则可能会导致扫描到的列表错误。
 * @date ：2025 5月 06 22:21
 */
public class JwtUrlHandler {

    private final List<String> extraAuthenticationUrlList = new ArrayList<>();
    private final List<String> extraIgnoreUrlList = new ArrayList<>();
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    /**
     * 默认的认证策略，所有url都不需要认证
     */
    private JwtAuthorityStrategy defaultAuthorityStrategy = JwtAuthorityStrategy.ALL_IGNORE;

    @Autowired
    public JwtUrlHandler(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        Assert.notNull(requestMappingHandlerMapping, "RequestMappingHandlerMapping must not be null");
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    /**
     * 调用SpringMvc框架的RequestMappingHandlerMapping的getHandlerMethods方法，获取所有HandlerMethod，
     * 并检查其是否带有@JwtAuthentication注解，如果有就返回true，否则返回false。
     *
     * @param request 请求对象
     * @return 若该请求需要进行JWT认证，则返回true，否则返回false
     * @throws MethodNotFoundException 未找到request对应的方法时抛出异常
     */
    public boolean matches(HttpServletRequest request) throws MethodNotFoundException {
        if (defaultAuthorityStrategy == JwtAuthorityStrategy.NOT_USED) {
            return true;
        }
        // 匹配是否在额外列表中
        String requestURI = request.getRequestURI();
        for (String extraUrl : extraAuthenticationUrlList) {
            if (requestURI.equals(extraUrl)) {
                return true;
            }
        }
        for (String ignoreUrl : extraIgnoreUrlList) {
            if (requestURI.equals(ignoreUrl)) {
                return false;
            }
        }
        // 匹配Controller中的url
        try {
            // 查找匹配的 HandlerMethod
            HandlerMethod handlerMethod = getHandlerMethod(request);
            if (handlerMethod != null) {
                // 获取方法对象
                Method method = handlerMethod.getMethod();
                // 检查方法 @JwtAuthority 注解的enabled属性的值
                JwtAuthority jwtAuthority = method.getAnnotation(JwtAuthority.class);
                if (jwtAuthority != null) {
                    return jwtAuthority.enabled();
                }
            }
        }
        catch (Exception e) {
            throw new MethodNotFoundException(e);
        }
        return switch (defaultAuthorityStrategy) {
            case ALL_AUTHORITY -> true;
            default -> false;
        };
    }

    /**
     * 获取匹配的HandlerMethod对象
     *
     * @param request 请求对象
     * @return HandlerMethod对象
     */
    private HandlerMethod getHandlerMethod(HttpServletRequest request) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            RequestMappingInfo match = info.getMatchingCondition(request);
            if (match != null) {
                return entry.getValue();
            }
        }
        return null;
    }

    public List<String> getExtraAuthorityUrlList() {
        return new ArrayList<>(extraAuthenticationUrlList);
    }

    public void addExtraAuthorityUrl(String extraAuthenticationUrl) {
        this.extraAuthenticationUrlList.add(extraAuthenticationUrl);
    }

    public void addExtraAuthorityUrl(List<String> extraAuthenticationUrlList) {
        this.extraAuthenticationUrlList.addAll(extraAuthenticationUrlList);
    }

    public List<String> getExtraIgnoreUrlList() {
        return extraIgnoreUrlList;
    }

    public void addExtraIgnoreUrl(String extraIgnoreUrl) {
        this.extraIgnoreUrlList.add(extraIgnoreUrl);
    }

    public void addExtraIgnoreUrl(List<String> extraIgnoreUrlList) {
        this.extraIgnoreUrlList.addAll(extraIgnoreUrlList);
    }

    public JwtAuthorityStrategy getDefaultAuthorityStrategy() {
        return defaultAuthorityStrategy;
    }

    public void setDefaultAuthorityStrategy(JwtAuthorityStrategy defaultAuthorityStrategy) {
        this.defaultAuthorityStrategy = defaultAuthorityStrategy;
    }
}