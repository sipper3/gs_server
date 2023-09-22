package kr.fingate.gs.auth.hstry.dto;

import kr.fingate.gs.comon.vo.HstryVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleHstryDto extends HstryVO {

    private String RoleType;
    private String roleName;
    private String roleGroupName;

}
