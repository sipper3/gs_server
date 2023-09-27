package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MdchtTakovRiderVO extends BaseVO {

    @Info(value="특약순번")
    private long riderSeq;

    @Info(value="병력인수번호")
    private long mdchtTakovNo;

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="분류코드")
    private String ctgryCode;

    @Info(value="특약코드")
    private String riderCode;

    @Info(value="특약가입금액")
    private BigInteger riderJoinAmount;

}
