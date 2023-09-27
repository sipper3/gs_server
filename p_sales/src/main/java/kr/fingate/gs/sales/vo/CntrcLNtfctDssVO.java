package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcLNtfctDssVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="보험대상자코드")
    private String insprsCode;

    @Info(value="질병순번")
    private long dssSeq;

    @Info(value="병명(진단명)")
    private String dssName;

    @Info(value="치료기간")
    private String curePeriod;

    @Info(value="치료내용")
    private String cureCntnt;

    @Info(value="입원여부")
    private String hsptlYn;

    @Info(value="입원/수술내용")
    private String hsptlCntnt;

    @Info(value="통원횟수")
    private int otptnTimes;

    @Info(value="통원내용")
    private String otptnCntnt;

    @Info(value="완치여부")
    private String flrcvYn;

    @Info(value="재발여부")
    private String rlpsYn;

    @Info(value="장애여부")
    private String obstcYn;

    @Info(value="보험금수령여부")
    private String insrnMoneyRcvYn;

    @Info(value="부담보부위")
    private String ntgntPart;

    @Info(value="부담보기간")
    private String ntgntPeriod;

    @Info(value="기타고지사항(메모)")
    private String etcNotice;

}
