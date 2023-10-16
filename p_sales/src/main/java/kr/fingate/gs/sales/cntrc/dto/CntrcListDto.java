package kr.fingate.gs.sales.cntrc.dto;

import kr.fingate.gs.common.annotation.Decrypt;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.annotation.Masking;
import kr.fingate.gs.common.annotation.MaskingType;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("CntrcListDto")
public class CntrcListDto {

    @Info(value = "등록일")
    private String regDate;

    @Info(value = "계약일")
    private String cntrcDate;

    @Info(value = "담당자 조직명")
    private String rspnsOrgnzName;

    @Info(value = "담당자명")
    private String rspnsUserName;

    @Info(value = "계약자")
    private String c0Name;

    @Info(value = "피보험자")
    private String p1Name;

    @Info(value = "피보험자 생일/출산일")
    private String p1Brthd;

    @Decrypt
    @Masking(value = MaskingType.TEL)
    @Info(value = "연락처")
    private String c0ClphnNo;

    @Info(value = "보험사")
    private String insrrCode;

    @Info(value = "대표상품명")
    private String rprsnPrdctName;

    @Info(value = "보험료")
    private String premTotal;

    @Info(value = "평가업적")
    private String premEvltn;

    @Info(value = "처리상태")
    private String sbscrStateType;

    @Info(value = "증권번호")
    private String policyNo;

    @Info(value = "계약상태(환수계약상태)")
    private String finalRdmptCntrcState;

    @Info(value = "수금상태(집금업무구분)")
    private String ctomyClass;

    @Info(value = "수금상세(집금업무구분 세부유형)")
    private String ctomyClassDtl;

    @Info(value = "보험기간")
    private String insrnPeriod;

    @Info(value = "납입기간")
    private String pymntPeriod;

    @Info(value = "자필상태")
    private String hndwrState;

    @Info(value = "상품구분")
    private String prdctClass;

    @Info(value = "최종처리자")
    private String finalStateUserName;

    @Info(value = "최종처리일시")
    private String finalStateDate;

    @Info(value = "계약번호")
    private String cntrcNo;

    @Info(value = "모집자")
    private String rcrtUserName;

    @Info(value = "청약최종처리자 - 청약완료처리자")
    private String c99StatePrscnUserName;

    @Info(value = "청약최종처리일 - 청약완료처리일")
    private String c99StatePrscnDate;

}
