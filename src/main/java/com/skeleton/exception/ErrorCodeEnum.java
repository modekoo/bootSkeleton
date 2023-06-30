package com.skeleton.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum {

    OK("200", "정상처리 되었습니다.", HttpStatus.OK),
    BAD_REQUEST("400", "잘못된요청입니다.", HttpStatus.BAD_REQUEST),

    NOT_FOUND("404", "해당 서비스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    INTERNAL_SERVER_ERROR("500", "서버오류", HttpStatus.INTERNAL_SERVER_ERROR),

    ;

    private String errorCode;
    private String errorMsg;
    private HttpStatus status;

    ErrorCodeEnum(String errorCode, String errorMsg, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.status = status;
    }

    public String getErrorCode(){
        return errorCode;
    }

    public String getErrorMsg(){
        return errorMsg;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
