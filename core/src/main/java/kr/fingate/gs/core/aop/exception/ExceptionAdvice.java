package kr.fingate.gs.core.aop.exception;

import kr.fingate.gs.core.aop.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     *  javax.validation.Valid or @Validated error binding
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        final ExceptionResponse response = new ExceptionResponse(ResponseCode.INVALID_PARAMETER.value(), ResponseCode.INVALID_PARAMETER.reasonPhrase(), getInValidedList(e.getBindingResult()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    protected List<Map<String, Object>> getInValidedList(final BindingResult bindingResult) {
        final List<Map<String, Object>> fieldList = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            Map<String, Object> errMap = new HashMap<>();

            errMap.put("field", fieldError.getField());
            errMap.put("defaultMessage", fieldError.getDefaultMessage());
//            errMap.put("rejectedValue", fieldError.getRejectedValue()); //사용자 입력값
            errMap.put("code", fieldError.getCode());
            fieldList.add(errMap);
        }

        return fieldList;
    }
}
