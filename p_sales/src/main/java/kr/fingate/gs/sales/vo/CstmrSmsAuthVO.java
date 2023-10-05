package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CstmrSmsAuthVO")
public class CstmrSmsAuthVO extends BaseVO {

    @Info(value="SMS동의순번")
    private long smsAgrmnSeq;

    @Info(value="인증번호")
    private String authNo;

    @Info(value="수신전화번호")
    private String rcvTlphnNo;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="발신번호")
    private String sendTlphnNo;

    @Info(value="발신자사용인번호")
    private long sendUserNo;

    @Info(value="메시지")
    private String msg;

    @Info(value="인증여부")
    private String authYn;

    @Info(value="인증수행자")
    private String authPrfrmUser;

    @Info(value="인증일시")
    private String authDt;

}
