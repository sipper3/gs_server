package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CstmrRspnsChangeVO extends BaseVO {

    @Info(value="변경순번")
    private long changeSeq;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="변경유형")
    private String changeType;

    @Info(value="기존담당자")
    private long exstnRspnsUserNo;

    @Info(value="현재담당자")
    private long crntRspnsUserNo;

    @Info(value="담당자변경사유")
    private String changeReason;

    @Info(value="처리상태")
    private String prcsnState;

    @Info(value="처리일시")
    private String prcsnDt;

    @Info(value="데이터상태")
    private String dataState;

}
