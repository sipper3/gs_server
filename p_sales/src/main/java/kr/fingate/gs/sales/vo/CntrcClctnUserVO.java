package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcClctnUserVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="등록순번")
    private long regSeq;

    @Info(value="사용인번호")
    private long userNo;

    @Info(value="데이터상태")
    private String dataStatus;

}
