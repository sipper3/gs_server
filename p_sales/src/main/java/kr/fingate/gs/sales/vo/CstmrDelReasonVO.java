package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CstmrDelReasonVO")
public class CstmrDelReasonVO extends BaseVO {

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="삭제사유코드")
    private String delReasonCode;

    @Info(value="삭제사유")
    private String delReason;

}
