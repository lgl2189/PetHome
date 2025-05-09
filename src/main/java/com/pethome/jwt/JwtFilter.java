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
        // 创建智能过滤链
        SmartJwtFilterChain smartChain = new SmartJwtFilterChain(filterChain, response);
        // 包装响应对象以监控输出
        HttpServletResponse wrappedResponse = smartChain.wrapResponse();

        try {
            // 调用用户实现的jwtAuthority方法
            jwtAuthority(request, wrappedResponse, smartChain);

            // 如果用户没有调用doFilter且响应未提交，则自动继续过滤链
            if (!smartChain.isFilterCalled() && !response.isCommitted()) {
                smartChain.doFilter(request, wrappedResponse);
            }
        }
        finally {
            // 清除缓存
            ServletRequestPathUtils.clearParsedRequestPath(request);
        }
    }


    /**
     * 验证token
     * @param request 请求对象
     * @param response 响应对象
     * @param filterChain 过滤链
     *
     * 用户应调用filterChain的doFilter方法以继续执行过滤链，
     * 当调用response的getWriter()、getOutputStream()、sendError()或sendRedirect()方法后，
     * 过滤链将自动停止，无需手动调用doFilter。
     */
    protected abstract void jwtAuthority(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull JwtFilterChain filterChain) throws ServletException, IOException;
}