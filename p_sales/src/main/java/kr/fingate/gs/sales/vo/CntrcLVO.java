package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcLVO")
public class CntrcLVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="보험종류")
    private String insrnKind;

    @Info(value="보험기간")
    private String insrnPeriod;

    @Info(value="납입기간")
    private String pymntPeriod;

    @Info(value="납입주기")
    private String pymntCycle;

    @Info(value="실보험기간")
    private int realInsrnPeriod;

    @Info(value="실납입기간")
    private int realPymntPeriod;

    @Info(value="총보험료")
    private BigInteger premTotal;

    @Info(value="월환산보험료")
    private BigInteger prmmm;

    @Info(value="수정보험료")
    private BigInteger premChange;

    @Info(value="실손보험료")
    private BigInteger premAtls;

    @Info(value="실손월환산보험료")
    private BigInteger prmmmAtls;

    @Info(value="실손수정보험료")
    private BigInteger premAtlsChange;

    @Info(value="입원일당가입여부")
    private String hspdaJoinYn;

    @Info(value="환산보험료2차년")
    private BigInteger premChange2;

    @Info(value="환산보험료3차년")
    private BigInteger premChange3;

    @Info(value="할인보험료")
    private BigInteger premDscnt;

    @Info(value="할인후월납환산보험료")
    private BigInteger prmmmDscnt;

}
