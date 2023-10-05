package kr.fingate.gs.common.exception;

public class BizException extends RuntimeException {

	private Object code;
	private String message;

	private static final long serialVersionUID = 1L;

	public BizException(String message) {
        super(message);
        this.code = BizError.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

	public BizException(BizError bizError) {
        super(bizError.valueOf(bizError.value()).getReasonPhrase());
        this.code = bizError.valueOf(bizError.value()).value();
        this.message = bizError.valueOf(bizError.value()).getReasonPhrase();
    }

	public BizException(BizError bizError, String message) {
		super(bizError.valueOf(bizError.value()).getReasonPhrase());
		this.code = bizError.valueOf(bizError.value()).value();
		this.message = message;
	}

	public BizException(BizError bizError, Throwable e) {
        super(bizError.valueOf(bizError.value()).getReasonPhrase(), e);
        this.code = bizError.valueOf(bizError.value()).value();
        this.message = bizError.valueOf(bizError.value()).getReasonPhrase();
    }

    public BizException(String message, Throwable e) {
        super(message, e);
        this.code = BizError.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

    @Override
	public String getMessage() {
    	return this.message;
    }

    public int getCode() {
    	return (Integer)this.code;
    }
}
