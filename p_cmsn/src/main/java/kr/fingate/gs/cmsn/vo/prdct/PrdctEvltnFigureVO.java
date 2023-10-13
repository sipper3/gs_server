package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctEvltnFigureVO")
public class PrdctEvltnFigureVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

    // 납입기간
    private int pymntPeriod;

    // 평가계수1차년
    private double evltnFigure1;

    // 평가계수2차년
    private double evltnFigure2;

    // 평가계수3차년
    private double evltnFigure3;

}
