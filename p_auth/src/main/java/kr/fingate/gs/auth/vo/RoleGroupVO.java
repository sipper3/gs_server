package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleGroupVO extends BaseVO {

    // 역할그룹번호
    private long roleGroupNo;

    // 클라이언트번호
    private long clientNo;

    // 역할그룹명
    private String roleGroupName;

    // 역할그룹설명
    private String roleGroupDscrp;

    // 사용여부
    private String useYn;

    // 데이터상태
    @JsonIgnore
    private  String dataState;
}
