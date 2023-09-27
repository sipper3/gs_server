package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CstmrDelBackupVO extends BaseVO {

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="고객명")
    private String cstmrName;

    @Info(value="주민(식별)번호")
    private String idntnNo;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="주민번호6자리")
    private String idntnNo6Dgt;

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

    @Info(value="자택전화")
    private String tlphnNo;

    @Info(value="자택전화4자리")
    private String tlphnNo4Dgt;

    @Info(value="이메일")
    private String email;

    @Info(value="자택우편번호")
    private String homeZpcd;

    @Info(value="자택주소")
    private String homeAdres;

    @Info(value="자택주소상세")
    private String homeAdresDtl;

    @Info(value="성별")
    private String gender;

    @Info(value="직장명")
    private String officeName;

    @Info(value="직장전화")
    private String officeTlphnNo;

    @Info(value="직장전화4자리")
    private String officeTlphnNo4Dgt;

}
