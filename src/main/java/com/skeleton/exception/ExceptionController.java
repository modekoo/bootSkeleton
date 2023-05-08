package com.skeleton.exception;

import com.skeleton.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
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
    public ResponseEntity<Object> handleError(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.valueOf(Integer.valueOf(statusCode.toString()));
        return ResponseEntity.status(status.value()).body(new CommonResponse(String.valueOf(status.value()), status.getReasonPhrase()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status.value()).body(new CommonResponse(String.valueOf(status.value()), ex.getAllErrors().stream().findFirst().get().getDefaultMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status.value()).body(new CommonResponse(String.valueOf(status.value()), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("exceptionHandler,,,\\n {}", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new CommonResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }

/*    @ExceptionHandler(APIException.class)
    public ResponseEntity<Object> handleAPIException(APIException e) {
    }*/
}
