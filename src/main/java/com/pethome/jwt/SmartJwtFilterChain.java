package com.pethome.jwt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：Star
 * @description ：JwtFilterChain的实现类，智能处理过滤链调用
 * @date ：2025 5月 09 17:38
 */
public class SmartJwtFilterChain implements JwtFilterChain {
    private final FilterChain originalChain;
    private boolean filterCalled = false;
    private final HttpServletResponse response;
    private boolean responseCommitted = false;

    public SmartJwtFilterChain(FilterChain originalChain, HttpServletResponse response) {
        this.originalChain = originalChain;
        this.response = response;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        if (filterCalled) {
            throw new IllegalStateException("Filter chain has already been processed");
        }
        // 检查响应是否已提交
        if (responseCommitted) {
            // 响应已提交，不再继续过滤链
            return;
        }
        filterCalled = true;
        originalChain.doFilter(request, response);
    }

    @Override
    public boolean isFilterCalled() {
        return filterCalled;
    }

    /**
     * 包装原始的HttpServletResponse，监控输出流和Writer的使用
     */
    public HttpServletResponse wrapResponse() {
        return new ResponseWrapper(response, this::onResponseUsed);
    }

    private void onResponseUsed() {
        this.responseCommitted = true;
    }
}

