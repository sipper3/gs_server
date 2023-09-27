package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CstmrDelReasonVO extends BaseVO {

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="삭제사유코드")
    private String delReasonCode;

    @Info(value="삭제사유")
    private String delReason;

}
