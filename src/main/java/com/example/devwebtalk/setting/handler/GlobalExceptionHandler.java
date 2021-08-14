package com.example.devwebtalk.setting.handler;

import com.example.devwebtalk.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-08
 * Time: 오후 9:14
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 응답을 400 에러로 리턴
     * @param e
     * @return
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult IllegalArgumentExceptionError(IllegalArgumentException e) {
        var result = new ErrorResult("IllegalArgumentException",e.getClass().getName(),e.getMessage());
        log.error("IllegalArgumentException [ERROR] " , e);
        return result;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResult exceptionError(Exception e) {
        var result = new ErrorResult("Exception",e.getClass().getName(),e.getMessage());
        log.error("exceptionError [ERROR] " , e);
        return result;
    }
}
