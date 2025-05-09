package com.pethome.jwt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 09 17:01
 */


public interface JwtFilterChain extends FilterChain {
    /**
     * 检查是否已调用doFilter方法
     * @return 如果已调用doFilter方法返回true，否则返回false
     */
    boolean isFilterCalled();
}