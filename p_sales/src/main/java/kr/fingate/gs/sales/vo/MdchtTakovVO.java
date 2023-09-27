package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MdchtTakovVO extends BaseVO {

    @Info(value="병력인수번호")
    private long mdchtTakovNo;

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="가입특약구분")
    private String joinRiderClass;

    @Info(value="메모")
    private String memo;

}
