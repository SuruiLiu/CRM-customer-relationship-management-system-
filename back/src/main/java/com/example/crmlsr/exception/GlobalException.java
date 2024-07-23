package com.example.crmlsr.exception;

import com.example.crmlsr.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * function：
 * author：lsr
 * date：2024/3/13 20:37
 */

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e){
        return Result.error("500",e.getMessage());
    }
}
