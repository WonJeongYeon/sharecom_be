package com.sharecom.sharecom_be.exception;

import com.sharecom.sharecom_be.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {


    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseException> handleException(BaseException e) {
        log.error(e.getMessage());
        return new BaseResponse<>(e.getStatus());
    }
}
