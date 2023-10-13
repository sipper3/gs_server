package kr.fingate.gs.cmsn.prdct.dto;

import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("SearchRprsnPrdctDto")
public class SearchRprsnPrdctDto extends PageVO {

    private long clientNo;
    
    // 대표상품코드
    private long rprsnPrdctCode;

    // 대표상품명
    private String rprsnPrdctName;

    // 보험사코드
    private String insrrCode;

    // 상품구분
    private String prdctClass;

    // 상품그룹
    private String prdctGroup;

    // 상품종류
    private String prdctKind;

    // 상품판매여부
    private String prdctSaleYn;

    // 등록 시작일
    private String startRegDate;
    
    // 등록 종료일
    private String endRegDate;

    // 확정여부
    private String dfntnYn;

}
