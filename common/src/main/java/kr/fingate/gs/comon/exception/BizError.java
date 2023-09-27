package kr.fingate.gs.comon.exception;

import org.springframework.lang.Nullable;

public enum BizError {

	SUCCESS(0, ""),

	INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다."),

	// LOGIN ERROR
	LOGN_NOT_USER(1000, "로그인 실패! 아이디 또는 비밀번호를 확인해주세요."),
	LOGN_LOCK_USER(1001, "해당 계정은 잠금 상태 입니다."),
	LOGN_EXPIRE_USER(1002, "개인정보보호와 안전한 사이트 이용을 위해 6개월 마다 비밀번호 변경을 권장하고 있습니다. 비밀번호를 변경해 주세요."),

	ACCESS_NOT_ALLOWED(3001, "허용되지 않는 접근입니다. "),

	HTTP_REQUEST_METHOD_NOT_SUPPORTED(3002, "지원하지 않는 Method입니다."),

	MISSING_PARAMETER(3003, "필수 파라미터가 누락 되었습니다."),

	INVALID_PARAMETER(3004, "파라미터가 올바르지 않습니다."),

	RQSTCLAS_NOT_SUPPORTED(3011, "지정되지 않은 요청구분 입니다."),

	NO_DATA_FOUND(3101, "검색결과가 없습니다."),

	NO_DATA_PROCESS(3102, "처리된 건이 없습니다."),

	NO_ITEMS_FOUND(3103, "처리대상건이 지정되지 않았습니다."),

	NO_FILES_FOUND(3104, "파일이 존재하지 않습니다."),

	NOT_ALLOW_FILES(3201, "허용되지 않은 파일형식입니다."),

	EXCEEDED_FILE_SIZE(3202, "업로드 파일 사이즈가 초과 되었습니다."),

	DUP_PARAMETER(3203, "중복데이터가 존재합니다."),

	ITEM_COUNT_MISMATCH(4001, "요청건수와 처리대상건수가 일치하지 않습니다."),

	UPLPAD_BUSY(4002, "현재 업로드 중인 데이타가 있습니다."),

	NOT_EXE_PROCESS(4003, "실행 할 수 없는 프로세스입니다."),

	FILE_NOT_CREATED(4004, "파일 생성에 실패하였습니다."),

	// SALS_STAF 전용, 필수 3종 고객 동의 인증 체크 용 (타 기능에서 사용 불가)
	SALS_STAF_UNAUTHORIZED(9401, "고객정보가 인증되지 않았습니다."),
	SALS_STAF_UNAUTHORIZED_OVER(9402, "실패회수를 초과 하였습니다.");

	private final int value;
	private final String reasonPhrase;

	BizError(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	@Override
	public String toString() {
		return this.value + " " + name();
	}

	public static BizError valueOf(int errorCode) {
		BizError error = resolve(errorCode);
		if (error == null) {
			error = BizError.INTERNAL_SERVER_ERROR;
//			throw new IllegalArgumentException("No matching constant for [" + errorCode + "]");
		}
		return error;
	}

	@Nullable
	public static BizError resolve(int statusCode) {
		for (BizError error : values()) {
			if (error.value == statusCode) {
				return error;
			}
		}
		return null;
	}
}
