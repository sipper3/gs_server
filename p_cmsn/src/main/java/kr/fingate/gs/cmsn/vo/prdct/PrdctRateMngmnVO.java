package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctRateMngmnVO")
public class PrdctRateMngmnVO extends BaseVO {

    // 클라이언트번호
    private long clientNo;

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private int prdctRateSeq;

    // 상품율군
    private String prdctRateGroup;

    // 적용시작일
    private String aplctStartDate;

    // 적용종료일
    private String aplctEndDate;

    // 확정여부
    private String dfntnYn;

    // 적용종료여부
    private String aplctEndYn;

    // 일시납성적적용여부
    private String onePymntRultYn;

    // 일시납지급율(직영)
    private double onePymntRate2;

    // 일시납평가계수(직영)
    private double onePymntEvltnFigure2;

    // 일시납지급율(비직영)
    private double onePymntRate1;

    // 일시납평가계수(비직영)
    private double onePymntEvltnFigure1;

}
