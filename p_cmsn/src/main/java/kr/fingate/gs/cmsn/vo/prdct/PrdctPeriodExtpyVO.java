package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctPeriodExtpyVO")
public class PrdctPeriodExtpyVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

    // 납입기간
    private int pymntPeriod;

    // 수수료유형
    private String extpyType;

    // RC조정율
    private double rcAdjstRate;

    // RC대표지급율
    private double rcRprsnPymntRate;


}
