package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcClctnUserVO")
public class CntrcClctnUserVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="등록순번")
    private long regSeq;

    @Info(value="수금자")
    private long clctnUserNo;

    @Info(value="데이터상태")
    private String dataStatus;

}
