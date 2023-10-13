package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctExtpyAdjstVO")
public class PrdctExtpyAdjstVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

    // 수수료유형
    private String extpyType;

    // 평가업적조정율
    private double evltnAdjstRate;

    // 지급액조정율
    private double prdctAdjstRate;

    // 보험사조정율적용여부
    private String insrrAdjstAplctYn;

    // 선지급율
    private double exAdpayRate;

    // 선지급회차(표기용)
    private String exAdpayTimes;

}
