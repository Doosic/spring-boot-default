package com.setup.nuxtspringdefaultsetup.exception;

import com.setup.nuxtspringdefaultsetup.constant.ErrorCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException{

    private final ErrorCode errorCode;

    public APIException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
