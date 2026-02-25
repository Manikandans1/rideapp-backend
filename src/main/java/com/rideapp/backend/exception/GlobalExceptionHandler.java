package com.rideapp.backend.exception;

import com.rideapp.backend.dto.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception ex){
        return new BaseResponse<>(
                false,
                ex.getMessage(),
                null
        );
    }
}