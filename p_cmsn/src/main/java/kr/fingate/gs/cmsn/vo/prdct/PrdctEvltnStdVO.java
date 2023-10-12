package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctEvltnStdVO")
public class PrdctEvltnStdVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

    // 납입기간
    private int pymntPeriod;

    // 기준환산률
    private double stdChangeRate;

    // 보험사환산률
    private double insrnChangeRate;

    // 상품환산률
    private double prdctChangeRate;

    // 납기별환산률
    private double periodChangeRate;


}
