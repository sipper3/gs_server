package kr.fingate.gs.auth.hstry.dto;

import kr.fingate.gs.common.vo.HstryVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("RoleHstryDto")
public class RoleHstryDto extends HstryVO {

    private String RoleType;
    private String roleName;
    private String roleGroupName;

}
