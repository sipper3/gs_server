package kr.fingate.gs.comon.exception;

public class BizException extends RuntimeException {

	private Object code;
	private String message;

	private static final long serialVersionUID = 1L;

	public BizException(String message) {
        super(message);
        this.code = BizError.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

	public BizException(BizError rionError) {
        super(rionError.valueOf(rionError.value()).getReasonPhrase());
        this.code = rionError.valueOf(rionError.value()).value();
        this.message = rionError.valueOf(rionError.value()).getReasonPhrase();
    }
	
	public BizException(BizError rionError, String message) {
		super(rionError.valueOf(rionError.value()).getReasonPhrase());
		this.code = rionError.valueOf(rionError.value()).value();
		this.message = message;
	}

	public BizException(BizError rionError, Throwable e) {
        super(rionError.valueOf(rionError.value()).getReasonPhrase(), e);
        this.code = rionError.valueOf(rionError.value()).value();
        this.message = rionError.valueOf(rionError.value()).getReasonPhrase();
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
