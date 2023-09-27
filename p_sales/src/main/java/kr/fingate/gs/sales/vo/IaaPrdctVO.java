package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IaaPrdctVO extends BaseVO {

    @Info(value="상품코드")
    private String productCd;

    @Info(value="상품명")
    private String procNm;

    @Info(value="보험사코드")
    private String inscoCd;

    @Info(value="보험사명")
    private String inscoNm;

    @Info(value="대분류")
    private String option1;

    @Info(value="대분류명")
    private String option1Nm;

    @Info(value="중분류")
    private String option2;

    @Info(value="중분류명")
    private String option2Nm;

    @Info(value="변액여부")
    private String isvary;

    @Info(value="변액여부명")
    private String isvaryNm;

    @Info(value="구분상세(A)")
    private String typea;

    @Info(value="구분상세(A)명")
    private String typeaNm;

    @Info(value="구분상세(B)")
    private String typeb;

    @Info(value="구분상세(B)명")
    private String typebNm;

    @Info(value="유니버셜여부")
    private String uniyn;

    @Info(value="유니버셜여부명")
    private String uniynNm;

    @Info(value="금리방식")
    private String inrate;

    @Info(value="금리방식명")
    private String inrateNm;

    @Info(value="등록일자")
    private String inDtm;

    @Info(value="수정일자")
    private String upDtm;

    @Info(value="판매시작일자")
    private String ssDtm;

    @Info(value="판매종료일자")
    private String seDtm;

    @Info(value="상품공개일자")
    private String useDtm;

    @Info(value="최신화일자")
    private String updateDtm;

    @Info(value="상품요약서 다운로드 URL")
    private String productSummary;

    @Info(value="DB상태")
    private String dbStat;

}
