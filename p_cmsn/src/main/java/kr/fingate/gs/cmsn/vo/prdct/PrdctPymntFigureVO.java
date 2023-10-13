package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctPymntFigureVO")
public class PrdctPymntFigureVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

    // 납입기간
    private int pymntPeriod;

    // 1차년지급율
    private double pymntRate1;

    // 2차년지급율
    private double pymntRate2;

    // 3차년지급율
    private double pymntRate3;

    // OA홀딩율
    private double oaHoldRate;

    // 지급조정율
    private double pymntAdjstRate;

    // 지급조정공시율
    private double pymntAlarmRate;

}
