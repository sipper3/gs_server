package kr.fingate.gs.core.aop.response;

public enum ResponseCode {
    OK(1, "OK"),
    FAIL(-1, "FAIL"),

    //TODO 필요한 시스템 정의 오류 항목 추가 필요

    FILE_NOT_FOUND(1000, "파일이 존재하지 않습니다."),
    FILE_NOT_ALLOW(1001, "허용되지 않은 파일형식입니다."),
    FILE_SIZE_EXCEEDED(1002, "업로드 파일 사이즈가 초과 되었습니다."),

    MISSING_PARAMETER(3003, "필수 파라미터가 누락 되었습니다."),

    // LOGIN ERROR
	LOGN_NOT_USER(1000, "아이디 또는 비밀번호를 확인해주세요."),
	LOGN_LOCK_USER(1001, "해당 계정은 잠금 상태 입니다."),
	LOGN_EXPIRE_USER(1002, "개인정보보호와 안전한 사이트 이용을 위해 6개월 마다 비밀번호 변경을 권장하고 있습니다. 비밀번호를 변경해 주세요.");

    private final int value;
    private final String reasonPhrase;

    ResponseCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }
    public String reasonPhrase() {
        return this.reasonPhrase;
    }
}
