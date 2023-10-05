package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("UserRoleVO")
public class UserRoleVO extends BaseVO {

    // 사용자번호
    private long userNo;

    // 역할번호
    private long roleNo;

    // 상시여부
    private String alwysYn;

    // 시작일
    private String startDate;

    // 종료일
    private String endDate;

    // 데이터상태
    @JsonIgnore
    private String dataState;

}
