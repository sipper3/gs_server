package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctPymntAlarmVO")
public class PrdctPymntAlarmVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

    // 대표환산적용여부
    private String regChangeYn;

    // 대표환산율
    private double regChangeRate;

    // 업적조정율
    private double resultAdjstRate;

    // 예시선지급회차
    private int exAdpayTimes;

    // 예시선지급회차2
    private int exAdpayTims2;

    // 총지급회차
    private int totalPymntTimes;

    // 총지급회차2
    private int totalPymntTims2;

    // 확정여부
    private String dfntnYn;

    // 메모
    private String alarmMemo;

}
