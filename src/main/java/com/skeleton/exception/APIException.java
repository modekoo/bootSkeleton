package com.skeleton.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIException extends RuntimeException {

    private final String errorMsg;
    private final String errorCode;
    private final HttpStatus status;

    public APIException(String errorMsg, String errorCode, HttpStatus status){
        super();
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.status = status;
    }

    public APIException(ErrorCodeEnum errorEnum){
        super();
        this.errorMsg = errorEnum.getErrorMsg();
        this.errorCode = errorEnum.getErrorCode();
        this.status = errorEnum.getStatus();
    }

    public APIException(ErrorCodeEnum errorEnum, String errorMsg){
        super();
        this.errorMsg = errorMsg;
        this.errorCode = errorEnum.getErrorCode();
        this.status = errorEnum.getStatus();
    }

    public APIException(ErrorCodeEnum errorEnum, Throwable e){
        super(e);
        this.errorMsg = errorEnum.getErrorMsg();
        this.errorCode = errorEnum.getErrorCode();
        this.status = errorEnum.getStatus();
    }

    public APIException(ErrorCodeEnum errorEnum, String errorMsg, Throwable e){
        super(e);
        this.errorMsg = errorMsg;
        this.errorCode = errorEnum.getErrorCode();
        this.status = errorEnum.getStatus();
    }

}
