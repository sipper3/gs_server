package kr.fingate.gs.sales.cntrc.dto;

import kr.fingate.gs.common.annotation.Decrypt;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.annotation.Masking;
import kr.fingate.gs.common.annotation.MaskingType;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Getter
@Setter
@Alias("SbscrListDto")
public class SbscrListDto {

    @Info(value = "계약번호")
    private String cntrcNo;

    @Info(value = "등록일")
    private String regDate;

    @Info(value = "계약일")
    private String cntrcDate;

    @Info(value = "모집자 조직명")
    private String rcrtOrgnzName;

    @Info(value = "모집자명")
    private String rcrtUserName;

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

    @Info(value = "상세상태")
    private String sbscrState;

//    @Info(value = "수신자")
//    private String rcvUserName;
//
//    @Info(value = "수신여부")
//    private String rcvYn;

    @Info(value = "증권번호")
    private String policyNo;

    @Info(value = "청약서파일등록일")
    private String cntrcFileRegDate;

//    @Info(value = "진단명건수")
//    private String dssCount;
//
//    @Info(value = "진단명")
//    private String dssName;

    @Info(value = "부담보사항")
    private String ntgntYn;

    @Info(value = "상태변경일시")
    private String prcsnDate;

    @Info(value = "상품구분")
    private String prdctClass;

    @Info(value = "앱전용상품")
    private String insrrAppCntrcYn;

    @Info(value = "처리내용")
    private String prcsnCntnt;

}
