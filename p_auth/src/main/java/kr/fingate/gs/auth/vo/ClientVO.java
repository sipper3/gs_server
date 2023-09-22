package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientVO extends BaseVO {

    // 클라이언트번호
    private long clientNo;

    // 클라이언트명
    private String clientName;


    // 클라이언트유형
    @JsonIgnore
    private String clientType;

    // 클라이언트상태
    @JsonIgnore
    private String clientState;

    // 데이터상태
    @JsonIgnore
    private String dataState;

}
