package com.pethome.jwt;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 09 17:01
 */


public interface JwtFilterChain {
    /**
     * 继续执行过滤链
     * @param request 请求对象
     * @param response 响应对象
     * @throws ServletException
     * @throws IOException
     */
    void doFilter(ServletRequest request, ServletResponse response) throws ServletException, IOException;

    /**
     * 检查是否已调用doFilter方法
     * @return 如果已调用doFilter方法返回true，否则返回false
     */
    boolean isFilterCalled();
}