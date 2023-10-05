package kr.fingate.gs.sales.cstmr.dto;

import kr.fingate.gs.common.annotation.Info;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@Alias("CstmrRspnsChangeSearchDto")
public class CstmrRspnsChangeSearchDto {

    @Info("고객번호")
    private long cstmrNo;

    @Info("처리상태")
    private String prcsnState;

    @Info("변경순번")
    private long changeSeq;
}
