package com.skeleton.exception;

import com.skeleton.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@RestController
public class ExceptionController<T> extends ResponseEntityExceptionHandler implements ErrorController {

    @RequestMapping(value = "/error")
    protected ResponseEntity<Object> handleError(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.valueOf(Integer.valueOf(statusCode.toString()));
        CommonResponse res = new CommonResponse(String.valueOf(status.value()), status.getReasonPhrase());
        return new ResponseEntity<>(res, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CommonResponse res = new CommonResponse(String.valueOf(status.value()), ex.getAllErrors().stream().findFirst().get().getDefaultMessage());
        return new ResponseEntity<>(res, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CommonResponse res = new CommonResponse(String.valueOf(status.value()), ex.getMessage());
        return new ResponseEntity<>(res, ErrorCodeEnum.INTERNAL_SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        log.error("ExceptionHandler : {}", ExceptionUtils.getStackTrace(e));
        CommonResponse res = new CommonResponse(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getErrorCode() , ErrorCodeEnum.INTERNAL_SERVER_ERROR.getErrorMsg());
        return new ResponseEntity<>(res, ErrorCodeEnum.INTERNAL_SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(APIException.class)
    protected ResponseEntity<Object> handleAPIException(APIException e) {
        log.error("APIExceptionHandler : {}", ExceptionUtils.getStackTrace(e));
        CommonResponse res = new CommonResponse(e.getErrorCode(), e.getErrorMsg());
        return new ResponseEntity<>(res, ErrorCodeEnum.INTERNAL_SERVER_ERROR.getStatus());
    }
}
