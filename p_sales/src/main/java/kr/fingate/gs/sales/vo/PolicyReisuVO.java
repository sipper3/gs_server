package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("PolicyReisuVO")
public class PolicyReisuVO extends BaseVO {

    @Info(value="재발행순번")
    private long reisuSeq;

    @Info(value="재발행유형")
    private String reisuType;

    @Info(value="처리상태")
    private String prcsnState;

    @Info(value="보험사코드")
    private String insrrCode;

    @Info(value="증권번호")
    private String policyNo;

    @Info(value="계약자명")
    private String cntrcName;

    @Info(value="파일여부")
    private String fileYn;

    @Info(value="출력여부")
    private String printYn;

    @Info(value="처리일시")
    private String prcsnDt;

    @Info(value="처리사용인번호")
    private long prcsnUserNo;

    @Info(value="데이터상태")
    private String dataState;

}
