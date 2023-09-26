package kr.fingate.gs.core.aop.response;

public enum ResponseCode {
    OK(1, "OK"),
    FAIL(-1, "FAIL"),

    //TODO 필요한 시스템 정의 오류 항목 추가 필요

    FILE_NOT_FOUND(1000, "파일이 존재하지 않습니다."),
    FILE_NOT_ALLOW(1001, "허용되지 않은 파일형식입니다."),
    FILE_SIZE_EXCEEDED(1002, "업로드 파일 사이즈가 초과 되었습니다.");

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
