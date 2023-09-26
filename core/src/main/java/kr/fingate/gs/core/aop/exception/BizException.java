package kr.fingate.gs.core.aop.exception;

import kr.fingate.gs.core.aop.response.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {
    private int code;
    private String message;

    public BizException(ResponseCode resCode) {
        super(resCode.reasonPhrase());
        this.code = resCode.value();
        this.message = resCode.reasonPhrase();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return (Integer)this.code;
    }
}
