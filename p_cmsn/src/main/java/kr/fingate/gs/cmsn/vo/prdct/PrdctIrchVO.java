package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctIrchVO")
public class PrdctIrchVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

    // 보험사환산순차
    private long irchSeq;

    // 보험사환산율기준
    private String irchRateStd;

    // 납입기간
    private int pymntPeriod;

    // 1차년원사환산율
    private double irchRate1;

    // 2차년원사환산율
    private double irchRate2;

    // 3차년원사환산율
    private double irchRate3;


}
