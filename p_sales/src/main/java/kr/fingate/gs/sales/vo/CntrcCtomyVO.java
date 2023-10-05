package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcCtomyVO")
public class CntrcCtomyVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="집금책임번호")
    private long ctomyRspnsNo;

    @Info(value="집금업무구분")
    private String ctomyClass;

    @Info(value="세부유형")
    private String ctomyClassDtl;

    @Info(value="최종환수계약상태")
    private String finalRdmptCntrcState;

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

    @Info(value="수금상태일(집금책임일)")
    private String ctomyRspnsDate;

    @Info(value="최종정산회차")
    private int finalCmsnEpsd;

    @Info(value="메모")
    private String memo;

    @Info(value="추징환급보험료")
    private BigInteger premChargeRefund;

    @Info(value="환산보험료2차년")
    private BigInteger premChange2;

    @Info(value="환산보험료3차년")
    private BigInteger premChange3;

    @Info(value="집금처리일")
    private String ctomyPrcsnDate;

    @Info(value="마감적용상태")
    private String closeAplctState;

    @Info(value="마감여부")
    private String closeYn;

    @Info(value="마감확정자")
    private long closeDfntnUserNo;

    @Info(value="마감확정일시")
    private String closeDfntnDt;

    @Info(value="최종납입회차")
    private int finalPymntEpsd;

    @Info(value="데이터상태")
    private String dataStatus;

}
