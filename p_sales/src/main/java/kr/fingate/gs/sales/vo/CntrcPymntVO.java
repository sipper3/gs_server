package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcPymntVO")
public class CntrcPymntVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="초회결제방법")
    private String pymntMethod1St;

    @Info(value="초회결제금융사")
    private String pymntFnnco1St;

    @Info(value="초회결제소유주명")
    private String pymntOwnerName1St;

    @Info(value="초회결제번호")
    private String pymntNo1St;

    @Info(value="초회결제유효기간")
    private String pymntExpiryPeriod1St;

    @Info(value="초회결제주민번호")
    private String pymntIdntnNo1St;

    @Info(value="계속결제방법")
    private String pymntMethod2Nd;

    @Info(value="계속결제금융사")
    private String pymntFnnco2Nd;

    @Info(value="계속결제소유주명")
    private String pymntOwnerName2Nd;

    @Info(value="계속결제번호")
    private String pymntNo2Nd;

    @Info(value="계속결제유효기간")
    private String pymntExpiryPeriod2Nd;

    @Info(value="계속결제출금일")
    private String pymntWthdrDate2Nd;

    @Info(value="계속결제주민번호")
    private String pymntIdntnNo2Nd;

    @Info(value="계속결제예금주연락처")
    private String pymntTlphnNo2Nd;

    @Info(value="계속결제관계")
    private String pymntRltn2Nd;

    @Info(value="계속보험료")
    private BigInteger premTotal2Nd;

    @Info(value="환급계좌금융사")
    private String refundAcntFnnco;

    @Info(value="환급계좌소유주명")
    private String refundAcntOwnerName;

    @Info(value="환급자주민번호")
    private String refundUserIdntnNo;

    @Info(value="환급계좌번호")
    private String refundAcntNo;

    @Info(value="환급자연락처")
    private String refundTlphnNo;

    @Info(value="환급자관계")
    private String refundUserRltn;

}
