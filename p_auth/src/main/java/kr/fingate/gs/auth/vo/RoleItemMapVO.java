package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleItemMapVO extends BaseVO {
    //역할번호
    private long roleNo;

    // 항목번호
    private long itemNo;

    // 데이터상태
    private String dataState;
}
