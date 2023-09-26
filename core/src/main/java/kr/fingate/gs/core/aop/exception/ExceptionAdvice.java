package kr.fingate.gs.core.aop.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice extends Exception{

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        final ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BizException.class)
    protected ResponseEntity<ExceptionResponse> handleBizException(BizException e) {
        final ExceptionResponse response = new ExceptionResponse(e.getCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//    TODO Exception 추가 필요
}
