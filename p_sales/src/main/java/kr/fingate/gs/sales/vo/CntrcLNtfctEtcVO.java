package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcLNtfctEtcVO")
public class CntrcLNtfctEtcVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="보험대상자코드")
    private String insprsCode;

    @Info(value="신장")
    private String height;

    @Info(value="몸무게")
    private String weight;

    @Info(value="BMI")
    private String bmi;

    @Info(value="혈압고")
    private String bpSystl;

    @Info(value="혈압저")
    private String bpDstlc;

    @Info(value="음주횟수코드")
    private String drnknTimesCode;

    @Info(value="음주량코드")
    private String drnknQtyCode;

    @Info(value="흡연기간코드")
    private String smokPeriodCode;

    @Info(value="흡연량코드")
    private String smokQtyCode;

    @Info(value="금연기간코드")
    private String nosmokPeriodCode;

    @Info(value="임신기간")
    private String prgnnPeriod;

    @Info(value="출산예정일")
    private String duedt;

    @Info(value="가족병력")
    private String familyMdcht;

    @Info(value="업무내용")
    private String bsnsCntnt;

    @Info(value="업무기간")
    private String bsnsPeriod;

    @Info(value="업무지역")
    private String bsnsRegion;

    @Info(value="업무목적")
    private String bsnsPrps;

    @Info(value="부업명")
    private String sideJobName;

    @Info(value="해외출국목적")
    private String ovrssPrps;

    @Info(value="해외출국기간")
    private String ovrssPeriod;

    @Info(value="해외출국지역")
    private String ovrssRegion;

    @Info(value="출국일")
    private String ovrssStartDate;

    @Info(value="입국일")
    private String ovrssEndDate;

    @Info(value="타가입보험사")
    private String otherCntrcInsrr;

    @Info(value="타가입보험료")
    private String otherCntrcPrem;

    @Info(value="타가입보험사질병보험건수")
    private String otherDssCntrcCount;

    @Info(value="타가입보험사상해보험건수")
    private String otherAcdntCntrcCount;

    @Info(value="승환계약건수")
    private String swapCntrcCount;

    @Info(value="총자산")
    private BigInteger totalAssets;

    @Info(value="년평균소득")
    private BigInteger aninc;

    @Info(value="주거형태")
    private String dwlngType;

    @Info(value="기타고지사항(메모)")
    private String etcNotice;

}
