package com.pethome.handler.exception;

import com.pethome.dto.Result;
import com.pethome.util.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 15 15:03
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 处理未捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleUnexpectedException(Exception e) {
        Map<String,String> result = new HashMap<>();
        result.put("message",e.getMessage());
        return ResultUtil.fail_500(result, "系统内部错误，请稍后再试");
    }

    // 处理404异常
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String,String> result = new HashMap<>();
        result.put("message",ex.getMessage());
        return ResultUtil.fail_404(result, "请求路径不存在");
    }
}