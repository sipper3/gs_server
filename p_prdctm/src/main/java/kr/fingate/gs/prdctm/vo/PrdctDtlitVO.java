package kr.fingate.gs.prdctm.vo;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctDtlitVO")
public class PrdctDtlitVO extends BaseVO {

    // 상품세목코드
    private String prdctDtlitCode;

    // 상품번호
    private long prdctNo;

    // 상품세목명
    private String prdctDtlitName;

    // 상품종
    private String prdctJong;

    // 상품형
    private String prdctHyung;

    // 납입면제여부
    private String pymntExmptYn;

    // 갱신조건
    private String renewCndtn;

    // 심사조건유형
    private String exmplCndtnType;

    // 심사조건유형상세
    private String exmplCndtnTypeDtl;

    // 해약환급금
    private String cancelRefund;

    // 보험사상품코드
    private String insrrPrdctCode;

    // 보험사상품종
    private String insrrPrdctJong;

    // 보험사상품형
    private String insrrPrdctHyung;

    // 보험사상품버전
    private String insrrPrdctVrsn;


}
