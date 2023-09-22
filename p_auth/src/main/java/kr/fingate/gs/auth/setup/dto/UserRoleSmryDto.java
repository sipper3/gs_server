package kr.fingate.gs.auth.setup.dto;

import kr.fingate.gs.auth.vo.UserVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRoleSmryDto extends UserVO {

    // 역할그룹이름
    private String roleGroupName;

    // 사용자역할여부
    private String userRoleYn;


}
