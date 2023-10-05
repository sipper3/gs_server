package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("IaaPrdctDtlVO")
public class IaaPrdctDtlVO extends BaseVO {

    @Info(value="상품코드")
    private String productCd;

    @Info(value="기본계약 (보장명/보장금액)")
    private String defaultContractGubun;

    @Info(value="보험금이 지급되지 않는 경우")
    private String contractConditionGuide;

    @Info(value="월 납입보험료(남)")
    private String mNapPremium;

    @Info(value="월 납입보험료(여)")
    private String wNapPremium;

    @Info(value="해지환급예시(남 1년 납입보험료)")
    private String mOneYearNapPremium;

    @Info(value="해지환급예시(남 1년 해지환급금)")
    private String mOneYearCanceRefundAmt;

    @Info(value="해지환급예시(남 1년 해지환급율)")
    private String mOneYearCanceRefundRate;

    @Info(value="해지환급예시(남 3년 납입보험료)")
    private String mThreeYearNapPremium;

    @Info(value="해지환급예시(남 3년 해지환급금)")
    private String mThreeYearCanceRefundAmt;

    @Info(value="해지환급예시(남 3년 해지환급율)")
    private String mThreeYearCanceRefundRate;

    @Info(value="해지환급예시(남 5년 납입보험료)")
    private String mFiveYearNapPremium;

    @Info(value="해지환급예시(남 5년 해지환급금)")
    private String mFiveYearCanceRefundAmt;

    @Info(value="해지환급예시(남 5년 해지환급율)")
    private String mFiveYearCanceRefundRate;

    @Info(value="해지환급예시(남 10년 납입보험료)")
    private String mTenYearNapPremium;

    @Info(value="해지환급예시(남 10년 해지환급금)")
    private String mTenYearCanceRefundAmt;

    @Info(value="해지환급예시(남 10년 해지환급율)")
    private String mTenYearCanceRefundRate;

    @Info(value="해지환급예시(남 20년 납입보험료)")
    private String mTwentyYearNapPremium;

    @Info(value="해지환급예시(남 20년 해지환급금)")
    private String mTwentyYearCanceRefundAmt;

    @Info(value="해지환급예시(남 20년 해지환급율)")
    private String mTwentyYearCanceRefundRate;

    @Info(value="해지환급예시(여 1년 납입보험료)")
    private String wOneYearNapPremium;

    @Info(value="해지환급예시(여 1년 해지환급금)")
    private String wOneYearCanceRefundAmt;

    @Info(value="해지환급예시(여 1년 해지환급율)")
    private String wOneYearCanceRefundRate;

    @Info(value="해지환급예시(여 3년 납입보험료)")
    private String wThreeYearNapPremium;

    @Info(value="해지환급예시(여 3년 해지환급금)")
    private String wThreeYearCanceRefundAmt;

    @Info(value="해지환급예시(여 3년 해지환급율)")
    private String wThreeYearCanceRefundRate;

    @Info(value="해지환급예시(여 5년 납입보험료)")
    private String wFiveYearNapPremium;

    @Info(value="해지환급예시(여 5년 해지환급금)")
    private String wFiveYearCanceRefundAmt;

    @Info(value="해지환급예시(여 5년 해지환급율)")
    private String wFiveYearCanceRefundRate;

    @Info(value="해지환급예시(여 10년 납입보험료)")
    private String wTenYearNapPremium;

    @Info(value="해지환급예시(여 10년 해지환급금)")
    private String wTenYearCanceRefundAmt;

    @Info(value="해지환급예시(여 10년 해지환급율)")
    private String wTenYearCanceRefundRate;

    @Info(value="해지환급예시(여 20년 납입보험료)")
    private String wTwentyYearNapPremium;

    @Info(value="해지환급예시(여 20년 해지환급금)")
    private String wTwentyYearCanceRefundAmt;

    @Info(value="해지환급예시(여 20년 해지환급율)")
    private String wTwentyYearCanceRefundRate;

    @Info(value="보험기간(갱신여부)")
    private String insurancePeriod;

    @Info(value="부가가능 특약수")
    private String bugaSpecialContractCnt;

    @Info(value="선택가입특약1(기본)")
    private String joinSpecialContractOneDefault;

    @Info(value="선택가입특약1(상세)")
    private String joinSpecialContractOneDetail;

    @Info(value="선택가입특약2(기본)")
    private String joinSpecialContractTwoDefault;

    @Info(value="선택가입특약2(상세)")
    private String joinSpecialContractTwoDetail;

    @Info(value="선택가입특약3(기본)")
    private String joinSpecialContractThreeDefault;

    @Info(value="선택가입특약3(상세)")
    private String joinSpecialContractThreeDetail;

    @Info(value="선택가입특약4(기본)")
    private String joinSpecialContractFourDefault;

    @Info(value="선택가입특약4(상세)")
    private String joinSpecialContractFourDetail;

    @Info(value="선택가입특약5(기본)")
    private String joinSpecialContractFiveDefault;

    @Info(value="선택가입특약5(상세)")
    private String joinSpecialContractFiveDetail;

    @Info(value="상품특징")
    private String productFeatures;

}
