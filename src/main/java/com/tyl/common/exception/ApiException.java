package com.tyl.common.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiException extends RuntimeException {

    private String errorCode;

    public ApiException(String errorCode) {
        this.errorCode = errorCode;
    }

    public ApiException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ApiException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
