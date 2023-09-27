package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcDocVO extends BaseVO {

    @Info(value="문서번호")
    private long docNo;

    @Info(value="고객명")
    private String cstmrName;

    @Info(value="친권자1")
    private String parentAuth1;

    @Info(value="친권자2")
    private String parentAuth2;

    @Info(value="고객구분")
    private String cstmrClass;

    @Info(value="생년월일/사업자번호")
    private String brthd;

    @Info(value="휴대전화")
    private String clphnNo;

    @Info(value="상품코드1")
    private int prdctCode1;

    @Info(value="상품코드2")
    private int prdctCode2;

    @Info(value="상품코드3")
    private int prdctCode3;

    @Info(value="청약상품코드")
    private int sbscrPrdctCode;

    @Info(value="보험대리점협회상품사용여부")
    private String iaaPrdctUseYn;

    @Info(value="보험대리점협회상품코드1")
    private String iaaPrdctCode1;

    @Info(value="보험대리점협회상품코드2")
    private String iaaPrdctCode2;

    @Info(value="보험대리점협회상품코드3")
    private String iaaPrdctCode3;

    @Info(value="보험대리점협회청약상품코드")
    private String iaaSbscrPrdctCode;

    @Info(value="최종서류다운일시")
    private String finalDocDwnldDt;

    @Info(value="SMS발송순번")
    private long smsSendSeq;

    @Info(value="최종문자발송일시")
    private String finalCharSendDt;

    @Info(value="고객회신일시")
    private String cstmrClbckDt;

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="최종청약연동일시")
    private String finalSbscrSynchDt;

    @Info(value="처리상태")
    private String prcsnState;

}
