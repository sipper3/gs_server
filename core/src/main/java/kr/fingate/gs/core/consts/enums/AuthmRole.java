package kr.fingate.gs.core.consts.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthmRole {

    /*
     * OAuth 인증 시 임시로 발급 받는 권한
     * 추가 데이터 입력 후 추가 회원가입 진행 시 USER로 전환
     */
    OAUTH("ROLE_OAUTH"),
    /*
     * 자체 회원가입 및 OAuth 회원가입이 모두 완료된 사용자
     * Guest 로그인을 하여도 USER로 취급함
     */
    USER("ROLE_USER");

    private final String key;
}