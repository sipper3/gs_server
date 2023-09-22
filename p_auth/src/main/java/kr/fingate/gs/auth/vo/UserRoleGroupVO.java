package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleGroupVO extends UserRoleVO {

    // 역할그룹번호
    private long roleGroupNo;

    // 데이터상태
    @JsonIgnore
    private String dataState;

}
