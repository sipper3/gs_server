package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcStatePrcsnVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="처리번호")
    private long prcsnNo;

    @Info(value="청약상태유형")
    private String sbscrStateType;

    @Info(value="청약상태")
    private String sbscrState;

    @Info(value="처리내용")
    private String prcsnCntnt;

    @Info(value="처리일")
    private String prcsnDate;

    @Info(value="처리시간")
    private String prcsnTime;

}
