package kr.fingate.gs.sales.cstmr.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.annotation.Info;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Builder
@Alias("DupCstmrSearchDto")
public class DupCstmrSearchDto {

    @Info("클라이언트번호")
    private long clientNo;

    @Info("고객번호")
    private long cstmrNo;

    @Info("담당자 사용인번호")
    private String rspnsUserNo;
    
    @Info(value="검색조건", description = "I=주민번호 / C=휴대전화번호")
    private String searchType;

    @Info("주민번호")
    private String idntnNo;
    @Info("주민번호(암호화)")
    @JsonIgnore
    private String encIdntnNo;

    @Info("휴대전화번호")
    private String clphnNo;
    @Info("휴대전화번호(암호화)")
    @JsonIgnore
    private String encClphnNo;
}
