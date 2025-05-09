package com.pethome.jwt;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ServletRequestPathUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：李冠良
 * @description ：用于验证token的Filter
 * @date ：2025 5月 02 19:07
 */

public abstract class JwtFilter extends OncePerRequestFilter {

    public JwtFilter() {
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 解析请求路径
        ServletRequestPathUtils.parseAndCache(request);
        try {
            jwtAuthority(request, response, filterChain);
        }
        finally {
            // 清除缓存
            ServletRequestPathUtils.clearParsedRequestPath(request);
        }
    }

    protected abstract void jwtAuthority(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException;
}