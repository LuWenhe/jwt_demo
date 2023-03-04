package com.example.jwt.exception;

import com.example.jwt.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 开启全局异常的捕获
@ControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常GlobalExceptionHandler捕获到ServiceException类抛出的异常
    // 需要以JSON字符串的格式返回结果
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result exceptionHandler(ServiceException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

}
