package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcDrftVO extends BaseVO {

    @Info(value="입력순번")
    private long drftSeq;

    @Info(value="상품구분")
    private String prdctClass;

    @Info(value="계약일")
    private String cntrcDate;

    @Info(value="보험사코드")
    private String insrrCode;

    @Info(value="계약등록타입")
    private String cntrcRegType;

    @Info(value="대표상품명")
    private String rprsnPrdctName;

    @Info(value="대표상품코드")
    private int rprsnPrdctCode;

    @Info(value="수금자명")
    private String clctnUserName;

    @Info(value="담당자")
    private long rspnsUserNo;

    @Info(value="증권번호")
    private String policyNo;

    @Info(value="총보험료")
    private BigInteger premTotal;

    @Info(value="보험시작일")
    private String insrnStartDate;

    @Info(value="보험종료일")
    private String insrnEndDate;

    @Info(value="보험기간")
    private String insrnPeriod;

    @Info(value="납입기간")
    private String pymntPeriod;

    @Info(value="납입주기")
    private String pymntCycle;

    @Info(value="월환산보험료")
    private BigInteger prmmm;

    @Info(value="수정보험료")
    private BigInteger premChange;

    @Info(value="자동차보험종류")
    private String carInsrnKind;

    @Info(value="자동차보험종목")
    private String carInsrnItem;

    @Info(value="차량번호")
    private String carNo;

    @Info(value="차량용도")
    private String carPrps;

    @Info(value="차량연식")
    private String carYear;

    @Info(value="물건구분")
    private String stuffClass;

    @Info(value="계약자명")
    private String cntrcName;

    @Info(value="계약자주민번호")
    private String cntrcIdntnNo;

    @Info(value="계약자휴대전화")
    private String cntrcClphn;

    @Info(value="계약자자택전화번호")
    private String cntrcTlphnNo;

    @Info(value="계약자자택우편번호")
    private String cntrcHomeZpcd;

    @Info(value="계약자자택주소")
    private String cntrcHomeAdres;

    @Info(value="계약자자택주소상세")
    private String cntrcHomeAdresDtl;

    @Info(value="피보험자명")
    private String insprsName;

    @Info(value="피보험자주민번호")
    private String insprsNoIdntn;

    @Info(value="피보험자휴대전화")
    private String insprsClphn;

    @Info(value="피보험자자택전화번호")
    private String insprsTlphnNo;

    @Info(value="피보험자자택우편번호")
    private String insprsHomeZpcd;

    @Info(value="피보험자자택주소")
    private String insprsHomeAdres;

    @Info(value="피보험자자택주소상세")
    private String insprsHomeAdresDtl;

    @Info(value="계약자관계코드")
    private String cntrcRltnCode;

    @Info(value="모집인코드")
    private String userCode;

    @Info(value="수금상태일(집금책임일)")
    private String ctomyRspnsDate;

    @Info(value="최종납입회차")
    private int finalPymntEpsd;

    @Info(value="데이터상태")
    private String dataStatus;

}
