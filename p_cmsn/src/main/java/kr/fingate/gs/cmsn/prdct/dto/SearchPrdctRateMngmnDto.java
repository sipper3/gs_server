package kr.fingate.gs.cmsn.prdct.dto;

import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Setter
@Getter
@Alias("SearchPrdctRateMngmnDto")
public class SearchPrdctRateMngmnDto extends PageVO {

    // 클라이언트번호
    private long clientNo;

    // 대표상품코드
    private long rprsnPrdctCode;

    // 적용종료여부
    private String aplctEndYn;

    // 상품율군 리스트
    private List<String> prdctRateGroupList;
}
