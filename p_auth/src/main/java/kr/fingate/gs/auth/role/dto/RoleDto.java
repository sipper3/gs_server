package kr.fingate.gs.auth.role.dto;

import kr.fingate.gs.auth.vo.RoleVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleDto extends RoleVO {

    // 그룹롤 이름
    private String roleGroupName;

    // 사용자롤 여부
    private String userRoleYn;


}
