package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcCVO")
public class CntrcCVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="갱신여부")
    private String renewYn;

    @Info(value="단기여부")
    private String shortTermYn;

    @Info(value="자동차보험종류")
    private String carInsrnKind;

    @Info(value="자동차보험종목")
    private String carInsrnItem;

    @Info(value="보험시작일")
    private String insrnStartDate;

    @Info(value="보험종료일")
    private String insrnEndDate;

    @Info(value="납입주기")
    private String carPymntCycle;

    @Info(value="물건구분")
    private String stuffClass;

    @Info(value="총보험료")
    private BigInteger premTotal;

    @Info(value="책임보험료")
    private BigInteger premRspns;

    @Info(value="임의보험료")
    private BigInteger premVlntr;

    @Info(value="입금(영수)보험료")
    private BigInteger premDpst;

    @Info(value="차대번호")
    private String vhclIdntnNo;

    @Info(value="전계약사")
    private String prevCntrcInsrr;

    @Info(value="전계약사할인할증")
    private String prevCntrcStandRate;

    @Info(value="동일증권여부")
    private String samePolicyYn;

    @Info(value="차량용도")
    private String carPrps;

    @Info(value="차량번호")
    private String carNo;

}
