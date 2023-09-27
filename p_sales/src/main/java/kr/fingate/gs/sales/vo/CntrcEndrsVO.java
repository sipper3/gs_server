package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcEndrsVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="배서번호")
    private long endrsNo;

    @Info(value="배서상태")
    private String endrsState;

    @Info(value="배서일")
    private String endrsDate;

    @Info(value="메모")
    private String memo;

    @Info(value="데이터상태")
    private String dataStatus;

}
