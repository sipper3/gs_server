package kr.fingate.gs.cmsn.vo.prdct;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("PrdctInsrrAdjstVO")
public class PrdctInsrrAdjstVO extends BaseVO {

    // 보험사코드
    private String insrrCode;

    // 상품군
    private String prdctGroup;

    // 조정율순차
    private int insrrAdjstSeq;

    // 클라이언트번호
    private long clientNo;

    // 보험사조정율
    private double insrrAdjstRate;

    // 적용시작일
    private String aplctStartDate;

    // 적용종료일
    private String aplctEndDate;

    // 확정여부
    private String dfntnYn;

    // 적용종료여부
    private String aplctEndYn;

}
