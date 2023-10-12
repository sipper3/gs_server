package kr.fingate.gs.cmsn.prdct.dto;

import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("SearchPrdctRateSeqDto")
public class SearchPrdctRateSeqDto extends PageVO {

    // 대표상품코드
    private long rprsnPrdctCode;

    // 상품율순차
    private long prdctRateSeq;

}
