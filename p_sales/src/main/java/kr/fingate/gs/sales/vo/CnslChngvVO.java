package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CnslChngvVO")
public class CnslChngvVO extends BaseVO {

    @Info(value="전환순번")
    private long chngvSeq;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="전환종류")
    private String chngvKind;

    @Info(value="전환상품종류")
    private String chngvPrdctKind;

    @Info(value="메모")
    private String memo;

    @Info(value="전환DB번호")
    private long chngvDbNo;

    @Info(value="전환상담번호")
    private long chngvCnslNo;

    @Info(value="처리상태")
    private String prcsnState;

    @Info(value="처리일시")
    private String prcsnDt;

}
