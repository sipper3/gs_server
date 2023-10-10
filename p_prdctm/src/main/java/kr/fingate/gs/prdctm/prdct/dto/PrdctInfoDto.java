package kr.fingate.gs.prdctm.prdct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.prdctm.vo.PrdctVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctInfoDto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrdctInfoDto extends PrdctVO {

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


}
