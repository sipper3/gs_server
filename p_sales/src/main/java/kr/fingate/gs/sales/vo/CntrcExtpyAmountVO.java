package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcExtpyAmountVO")
public class CntrcExtpyAmountVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="상품율순번")
    private long prdctRateSeq;

    @Info(value="총지급액")
    private BigDecimal prdctPymntTotal;

    @Info(value="평가업적")
    private BigDecimal premEvltn;

    @Info(value="예외지급유형")
    private String excptPayType;

    @Info(value="메모")
    private String memo;

    @Info(value="1차년평가계수")
    private BigDecimal evltnFgrs1St;

    @Info(value="2차년평가계수")
    private BigDecimal evltnFgrs2Nd;

    @Info(value="3차년평가계수")
    private BigDecimal evltnFgrs3Rd;

    @Info(value="일시납성적적용")
    private String onePayRgltYn;

    @Info(value="1차년지급율")
    private BigDecimal payRate1St;

    @Info(value="2차년지급율")
    private BigDecimal payRate2Nd;

    @Info(value="3차년지급율")
    private BigDecimal payRate3Rd;

    @Info(value="OA홀딩율")
    private BigDecimal payAmountFgrs4;

    @Info(value="지급조정율")
    private BigDecimal payAmountFgrs5;

    @Info(value="조직조정율")
    private BigDecimal payAmountFgrs6;

    @Info(value="DB조정율")
    private BigDecimal dbAdjstRate;

    @Info(value="보험사조정율")
    private BigDecimal insrrAdjstRate;

    @Info(value="수수료유형")
    private String extpyType;

    @Info(value="평가업적조정율")
    private BigDecimal evltnResultAdjstRate;

    @Info(value="지급액조정율")
    private BigDecimal pymntAdjstRate;

    @Info(value="관리자평가업적")
    private BigDecimal mngmnEvltnResult;

    @Info(value="구간지급율")
    private BigDecimal sctnPayRate;

    @Info(value="RC조정율")
    private BigDecimal rcAdjstRate;

    @Info(value="조정평가업적")
    private BigDecimal adjstPremEvltn;

    @Info(value="RC대표지급율")
    private BigDecimal rcRprsnPayRate;

    @Info(value="상품율군")
    private String prdctRateGroup;

    @Info(value="구간지급율_규정번호")
    private long sectRateNo;

    @Info(value="영업수수료구분")
    private String salesExtpyClass;

    @Info(value="정산제외구분")
    private String cmsnExclsClass;

}
