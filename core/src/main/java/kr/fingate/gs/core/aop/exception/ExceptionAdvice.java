package kr.fingate.gs.core.aop.exception;

import kr.fingate.gs.core.aop.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

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

    protected List<Map.Entry<String, List<Map<String, Object>>>> getInValidedList(final BindingResult bindingResult) {
        Map<String, List<Map<String, Object>>> errCon = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            Map<String, Object> errMap = new HashMap<>();

            String field = fieldError.getField();
            errMap.put("code", fieldError.getCode());
            errMap.put("message", fieldError.getDefaultMessage());
//            errMap.put("rejectedValue", fieldError.getRejectedValue()); //사용자 입력값
            if (Objects.equals(fieldError.getCode(), "Conditional")) {
                errMap.put("required", Objects.requireNonNull(fieldError.getArguments())[2]);
            }

            if (errCon.get(field) != null) {
                errCon.get(field).add(errMap);
            } else {
                List<Map<String, Object>> list = new ArrayList<>();
                list.add(errMap);
                errCon.put(field, list);
            }
        }

        return new ArrayList<>(errCon.entrySet());
    }
}
