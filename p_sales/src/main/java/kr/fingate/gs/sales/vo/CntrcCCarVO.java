package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcCCarVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="차량종류")
    private String carKind;

    @Info(value="스포츠카여부")
    private String sportsCarYn;

    @Info(value="차량등록일")
    private String carRegDate;

    @Info(value="동부차량등록일")
    private String n11RegDate;

    @Info(value="차명코드")
    private String carCode;

    @Info(value="대표차명여부")
    private String rprsnCarYn;

    @Info(value="차량연식")
    private String carYear;

    @Info(value="배기량")
    private String dsplc;

    @Info(value="배기코드")
    private String dsplcCode;

    @Info(value="인원/톤")
    private String prsnl;

    @Info(value="대표차명배기량/인원(차명코드부재시)")
    private String rprsnDspmPptn;

    @Info(value="대표차명배기코드(차명코드부재시)")
    private String rprsnDspmCode;

    @Info(value="차량명")
    private String carName;

    @Info(value="차량등급")
    private String carGrade;

    @Info(value="LPG차량여부")
    private String lpgCarYn;

    @Info(value="차량가액")
    private BigInteger carPrice;

    @Info(value="일부담보금액(차량가액일부)")
    private BigInteger carPricePart;

    @Info(value="부속품가액")
    private BigInteger acsryPrice;

    @Info(value="에어백")
    private String airbag;

    @Info(value="자동변속기")
    private String atmtr;

    @Info(value="ABS")
    private String abs;

    @Info(value="도난방지(IM)")
    private String sufa;

    @Info(value="블랙박스")
    private String bkbox;

    @Info(value="안전주행장치")
    private String scc;

    @Info(value="현대블루링크")
    private String blueLink;

    @Info(value="기타부속품명")
    private String etcAcsryName;

    @Info(value="기타부속품가액")
    private int etcAcsryPrice;

}
