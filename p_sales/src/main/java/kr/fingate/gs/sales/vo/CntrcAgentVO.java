package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcAgentVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="소개유형")
    private String agentType;

    @Info(value="소개자번호")
    private long agnetUserNo;

    @Info(value="소개자명")
    private String agnetName;

    @Info(value="주민번호")
    private String idntnNo;

    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="이메일")
    private String email;

    @Info(value="소속")
    private String afltn;

    @Info(value="은행")
    private String bank;

    @Info(value="계좌번호")
    private String acntNo;

    @Info(value="자택우편번호")
    private String homeZpcd;

    @Info(value="자택주소")
    private String homeAdres;

    @Info(value="자택주소상세")
    private String homeAdresDtl;

    @Info(value="데이터상태")
    private String dataStatus;

}
