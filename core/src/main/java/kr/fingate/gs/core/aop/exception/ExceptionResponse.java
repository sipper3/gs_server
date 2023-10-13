package kr.fingate.gs.core.aop.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private int code;
    private String message;

    private Object data;


    @Builder
    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Builder
    public ExceptionResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
