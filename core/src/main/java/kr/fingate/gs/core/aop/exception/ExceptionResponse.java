package kr.fingate.gs.core.aop.exception;

import lombok.Builder;
import lombok.Data;

@Data
public class ExceptionResponse {
    private int code;
    private String message;


    @Builder
    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
