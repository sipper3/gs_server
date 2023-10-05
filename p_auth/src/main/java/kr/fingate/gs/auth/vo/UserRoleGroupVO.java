package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("UserRoleGroupVO")
public class UserRoleGroupVO extends UserRoleVO {

    // 역할그룹번호
    private long roleGroupNo;

    // 데이터상태
    @JsonIgnore
    private String dataState;

}
