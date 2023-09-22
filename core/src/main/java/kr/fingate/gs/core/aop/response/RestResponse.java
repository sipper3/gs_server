package kr.fingate.gs.core.aop.response;

import kr.fingate.gs.core.aop.exception.ExceptionResponse;
import lombok.Builder;
import lombok.Data;

@Data
public class RestResponse<T> {
    private String result;              // OK, FAIL
    private ExceptionResponse error;
    private T data;

    public RestResponse() {}

    @Builder
    public RestResponse(ResponseCode code, ExceptionResponse error, T data) {
        this.result = code.isResult();
        this.error = error;
        this.data = data;
    }
}
