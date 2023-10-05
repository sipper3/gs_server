package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CloseSalesDfntnVO")
public class CloseSalesDfntnVO extends BaseVO {

    @Info(value="마감년월")
    private String closeYymm;

    @Info(value="마감여부")
    private String closeYn;

    @Info(value="마감확정일시")
    private String closeDfntnDt;

    @Info(value="수행회차")
    private int prfrmEpsd;

    @Info(value="수행건수")
    private int prfrmCount;

    @Info(value="수행상태")
    private String prfrmState;

    @Info(value="수행메모")
    private String prfrmMemo;

    @Info(value="수행일시")
    private String prfrmDt;

    @Info(value="사전마감회차")
    private int prevCloseEpsd;

    @Info(value="사전마감상태")
    private String prevCloseState;

}
