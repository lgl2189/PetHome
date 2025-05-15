package com.pethome.config.web.binder;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 15 10:45
 */


public class SnakeToCamelArgumentProcessor extends ServletModelAttributeMethodProcessor {

    /**
     * Class constructor.
     *
     * @param annotationNotRequired if "true", non-simple method arguments and
     *                              return values are considered model attributes with or without a
     *                              {@code @ModelAttribute} annotation
     */
    public SnakeToCamelArgumentProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(@NonNull WebDataBinder binder, @NonNull NativeWebRequest request) {
        // 获取请求的 Content-Type
        ServletRequest originRequest = request.getNativeRequest(ServletRequest.class);
        if (originRequest == null) {
            System.out.println("bindRequestParameters接收到一个空的ServletRequest，建议检查代码逻辑");
            return;
        }
        String contentType = originRequest.getContentType();
        // 仅处理表单请求（multipart/form-data 或 application/x-www-form-urlencoded）
        if (contentType != null &&
                (contentType.startsWith("multipart/form-data")
                        || contentType.startsWith("application/x-www-form-urlencoded")
                )
        ) {
            Object target = binder.getTarget();
            SnakeToCamelServletRequestDataBinder dataBinder = new SnakeToCamelServletRequestDataBinder(target, binder.getObjectName());
            super.bindRequestParameters(dataBinder, request);
        }
        else {
            super.bindRequestParameters(binder, request);
        }
    }

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return super.supportsParameter(parameter);
    }
}