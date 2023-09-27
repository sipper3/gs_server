package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcCRltnVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="보험대상자코드")
    private String insprsCode;

    @Info(value="자동차관계자구분")
    private String carRltnUserClass;

    @Info(value="자동차관계자코드")
    private String carRltnUserCode;

}
