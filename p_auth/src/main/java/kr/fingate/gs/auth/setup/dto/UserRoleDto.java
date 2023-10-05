package kr.fingate.gs.auth.setup.dto;

import kr.fingate.gs.auth.vo.UserRoleVO;
import kr.fingate.gs.comon.annotation.ComparableEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
//@ComparableEntity("GA_USER_ROLE")
@Alias("UserRoleDto")
public class UserRoleDto extends UserRoleVO {


}
