package kr.fingate.gs.auth.vo;

import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("RoleGroupMapVO")
public class RoleGroupMapVO extends BaseVO {

    // 역할그룹번호
    private long roleGroupNo;

    // 역할번호
    private long roleNo;

    // 데이터상태
    private  String dataState;
}
