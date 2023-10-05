package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CstmrDelResultVO")
public class CstmrDelResultVO extends BaseVO {

    @Info(value="삭제순번")
    private long delSeq;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="삭제유형")
    private String delType;

    @Info(value="삭제일")
    private String delDate;

    @Info(value="삭제완료여부")
    private String delCmpltYn;

}
