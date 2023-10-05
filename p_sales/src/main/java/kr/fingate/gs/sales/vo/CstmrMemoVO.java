package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CstmrMemoVO")
public class CstmrMemoVO extends BaseVO {

    @Info(value="메모순번")
    private long memoSeq;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="처리구분")
    private String prcsnClass;

    @Info(value="메모")
    private String memo;

    @Info(value="데이터상태")
    private String dataState;

}
