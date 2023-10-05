package kr.fingate.gs.sales.cstmr.dto;

import kr.fingate.gs.common.annotation.Info;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@Alias("CstmrSearchDto")
public class CstmrSearchDto {
    
    @Info("고객번호")
    private long cstmrNo;
}
