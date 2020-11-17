package com.tyl.common.exception;

import com.tyl.common.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiException extends RuntimeException {

    private String message;
    private String errorCode;

    public ApiException(String message) {
        this.message = message;
        this.errorCode = ResultCode.VALIDATE_FAILED.getCode();
    }
}
