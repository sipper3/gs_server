package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleVO extends BaseVO {

    // 역할번호
    private  long roleNo;

    // 클라이언트번호
    @JsonIgnore
    private  long clientNo;

    // 역할명
    private  String roleName;

    // 역할설명
    private  String roleDscrp;

    // 사용여부
    private  String useYn;

    // 데이터상태
    @JsonIgnore
    private  String dataState;
}
