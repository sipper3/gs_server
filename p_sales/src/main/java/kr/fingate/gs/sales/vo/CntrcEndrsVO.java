package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcEndrsVO")
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
