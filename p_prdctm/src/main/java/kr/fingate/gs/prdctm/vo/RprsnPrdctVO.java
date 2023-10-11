package kr.fingate.gs.prdctm.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("RprsnPrdctVO")
public class RprsnPrdctVO extends BaseVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 대표상품명
    private String rprsnPrdctName;

    // 대표상품이명
    private String rprsnPrdctNcknm;

    // 보험사코드
    private String insrrCode;

    // 상품구분
    private String prdctClass;

    // 상품그룹
    private String prdctGroup;

    // 상품종류
    private String prdctKind;

    // 상품판매유형
    private String prdctSaleType;

    // 상품판매여부
    private String prdctSaleYn;

    // 상품노출여부
    private String prdctExpsrYn;

    // 상품판매시작일
    private String prdctSaleStartDate;

    // 상품판매종료일
    private String prdctSaleEndDate;

    // 이전대표상품코드
    private String prevRprsnPrdctCode;

    // 데이터상태
    @JsonIgnore
    private String dataState;

}
