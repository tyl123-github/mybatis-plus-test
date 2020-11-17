package com.tyl.common.exception;

import com.tyl.common.response.ApiResponse;
import com.tyl.common.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public Response handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return ApiResponse.error(e.getErrorCode());
        }
        return ApiResponse.error(e.getMessage());
    }
}
