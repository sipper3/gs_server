package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CnslDelBackupVO extends BaseVO {

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="이름")
    private String name;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="성별")
    private String gender;

    @Info(value="데이터상태")
    private String dataState;

    @Info(value="휴대전화")
    private String clphnNo;

    @Info(value="휴대전화4자리")
    private String clphnNo4Dgt;

    @Info(value="기타(추가)전화")
    private String etcTlphnNo;

    @Info(value="기타(추가)전화4자리")
    private String etcTlphnNo4Dgt;

    @Info(value="최종통화번호(삭제)")
    private String lastCallNo;

    @Info(value="안심번호")
    private String safeTlphnNo;

    @Info(value="이메일")
    private String email;

}
