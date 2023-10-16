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

    public BizException(ResponseCode resCode, String msg) {
        super(resCode.reasonPhrase());
        this.code = resCode.value();
        this.message = msg;
    }

    public BizException(ResponseCode resCode, Throwable e) {
        super(resCode.reasonPhrase(), e);
        this.code = resCode.value();
        this.message = resCode.reasonPhrase();
    }

    public BizException(String msg) {
        super(msg);
        this.code = ResponseCode.INTERNAL_SERVER_ERROR.value();
        this.message = msg;
    }

    public BizException(String msg, Throwable e) {
        super(msg, e);
        this.code = ResponseCode.INTERNAL_SERVER_ERROR.value();
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return (Integer)this.code;
    }
}
