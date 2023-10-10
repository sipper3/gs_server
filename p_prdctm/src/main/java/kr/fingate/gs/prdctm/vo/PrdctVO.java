package kr.fingate.gs.prdctm.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctVO")
public class PrdctVO extends BaseVO {

    // 상품번호
    private long prdctNo;

    // 대표상품코드
    private int rprsnPrdctCode;

    // 보험사코드
    private String insrrCode;

    // 상품명
    private String prdctName;

    // 상품이명
    private String prdctNcknm;

    // 상품노출여부
    private String prdctExpsrYn;

    // 판매시작일
    private String saleStartDate;

    // 판매종료일
    private String saleEndDate;

    // 상품상태
    private String prdctState;

    // 판매채널
    private String saleChnl;

    // 전자청약여부
    private String elctrSbscrYn;

    // 보험료산출여부
    private String premClcltYn;

    // 보험료산출방법
    private String premClcltMethod;

    // 보험료산출유형
    private String premClcltType;

    // 화폐유형
    private String crncyType;

    // 급수유형
    private String gradeType;

    // 출력순서
    private int printOrder;

    // 데이터상태
    @JsonIgnore
    private String dataState;

}
