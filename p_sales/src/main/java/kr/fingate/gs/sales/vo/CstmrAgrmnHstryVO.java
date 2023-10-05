package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CstmrAgrmnHstryVO")
public class CstmrAgrmnHstryVO extends BaseVO {

    @Info(value="동의순번")
    private long agrmnSeq;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="동의구분")
    private String agrmnClass;

    @Info(value="동의여부")
    private String agrmnYn;

    @Info(value="동의유형")
    private String agrmnType;

    @Info(value="동의일")
    private String agrmnDate;

    @Info(value="동의기간")
    private int agrmnPeriod;

    @Info(value="동의만료일")
    private String agrmnExprtDate;

    @Info(value="동의수행자")
    private String agrmnPrfrmUser;

    @Info(value="전화수신동의여부")
    private String tlphnRcvAgrmnYn;

    @Info(value="SMS수신동의여부")
    private String smsRcvAgrmnYn;

    @Info(value="이메일수신동의여부")
    private String emailRcvAgrmnYn;

}
