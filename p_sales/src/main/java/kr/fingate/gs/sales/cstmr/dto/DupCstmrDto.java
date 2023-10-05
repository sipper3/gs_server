package kr.fingate.gs.sales.cstmr.dto;

import kr.fingate.gs.common.annotation.Info;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Builder
@Alias("DupCstmrDto")
public class DupCstmrDto {

    @Info("클라이언트번호")
    private long clientNo;

    @Info("고객번호")
    private long cstmrNo;

    @Info("담당자 사용인번호")
    private String rspnsUserNo;

    @Info("고객명")
    private String cstmrName;
    
    @Info("등록일")
    private String regDate;
}
