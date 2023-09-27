package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InsrrNbsnUploadVO extends BaseVO {

    @Info(value="등록순번")
    private long regSeq;

    @Info(value="번호")
    private int no;

    @Info(value="보험사코드")
    private String insrrCode;

    @Info(value="증권번호")
    private String policyNo;

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="계약일")
    private String cntrcDate;

    @Info(value="총보험료")
    private BigInteger premTotal;

    @Info(value="수정보험료")
    private BigInteger premChange;

    @Info(value="환산보험료2차년")
    private BigInteger premChange2;

    @Info(value="환산보험료3차년")
    private BigInteger premChange3;

    @Info(value="상품코드")
    private int prdctCode;

    @Info(value="태아등재일")
    private String unbornModDate;

    @Info(value="출산후정산여부")
    private String prcrtAfterCalcYn;

    @Info(value="납입기간")
    private String pymntPeriod;

    @Info(value="태아등재차감회차")
    private int unbornModDdctnEpsd;

}
