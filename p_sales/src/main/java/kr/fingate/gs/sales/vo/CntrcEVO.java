package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcEVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="보험시작일")
    private String insrnStartDate;

    @Info(value="보험시작시간")
    private String insrnStartTime;

    @Info(value="보험종료일")
    private String insrnEndDate;

    @Info(value="보험종료시간")
    private String insrnEndTime;

    @Info(value="물건구분")
    private String stuffClass;

    @Info(value="목적")
    private String prps;

    @Info(value="총보험료")
    private BigInteger premTotal;

    @Info(value="수정보험료")
    private BigInteger premChange;

    @Info(value="납입주기")
    private String pymntCycle;

    @Info(value="입금(영수)보험료")
    private BigInteger premDpst;

}
