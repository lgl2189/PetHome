package com.pethome.filter;

import com.pethome.util.StringUtil;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 05 12:28
 */


public class ParamNameSnakeToCamelFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final Map<String, String[]> parameters = new ConcurrentHashMap<>();
        final ArrayList<Part> partList = new ArrayList<>();

        // 处理普通参数
        for (String param : request.getParameterMap().keySet()) {
            String camelCaseParam = StringUtil.snakeToCamel(param);
            parameters.put(camelCaseParam, request.getParameterValues(param));
            parameters.put(param, request.getParameterValues(param));
        }
        // 如果是MultiPart请求，则进行Part参数转换，否则会导致错误
        if (MultipartResolutionDelegate.isMultipartRequest(request)) {
            // 处理文件参数
            for (Part part : request.getParts()) {
                String originalName = part.getName();
                String camelCaseName = StringUtil.snakeToCamel(originalName);
                // 将ApplicationPart对象转换为EnhancedApplicationPart对象，并将其放入partMap中
                EnhancedCamelPart enhancedCamelPart = new EnhancedCamelPart(part);
                partList.add(part);
                partList.add(enhancedCamelPart);
            }
        }

        filterChain.doFilter(new HttpServletRequestWrapper(request) {
            @Override
            public String getParameter(String name) {
                return parameters.containsKey(name) ? parameters.get(name)[0] : null;
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(parameters.keySet());
            }

            @Override
            public String[] getParameterValues(String name) {
                return parameters.get(name);
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return parameters;
            }

            @Override
            public Part getPart(String name) {
                for (Part part : partList) {
                    if (name.equals(part.getName())) {
                        return part;
                    }
                }
                return null;
            }

            @Override
            public Collection<Part> getParts() {
                return partList;
            }
        }, response);
    }
}