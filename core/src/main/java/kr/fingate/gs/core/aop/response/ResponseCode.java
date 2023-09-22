package kr.fingate.gs.core.aop.response;

import lombok.Getter;

public enum ResponseCode {
    OK(1, "OK"),
    FAIL(-1, "FAIL");

    //TODO 필요한 시스템 정의 오류 항목 추가 필요


    private final int value;
    private final String reasonPhrase;

    ResponseCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }
    public String isResult() {
        return this.reasonPhrase;
    }
}
