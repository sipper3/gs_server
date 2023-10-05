package kr.fingate.gs.common.consts.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public enum RedisKey {

    GS_COMM_CODE("gs_code", "공통코드"),
    GS_USER_INFO("gs_userinfo", "사용자 정보"),


//    /**
//     * use : 테스트용
//     * key : core:test:{userNo}
//     * data Type : String
//     * comment : 필요 시 부가설명
//     */
//    CORE_TEST("core:test");
    CORE_TEST("core:test", "test");

    private String path;
    private String pathName;
}
