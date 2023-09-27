package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcCorpSprtVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="법인영업지원서비스종류")
    private String corpSalesSprtSrvcKind;

    @Info(value="세무여부")
    private String corpSprtFieldYnA;

    @Info(value="노무여부")
    private String corpSprtFieldYnB;

    @Info(value="법무여부")
    private String corpSprtFieldYnC;

    @Info(value="인증여부")
    private String corpSprtFieldYnD;

    @Info(value="기타여부")
    private String corpSprtFieldYnE;

}
