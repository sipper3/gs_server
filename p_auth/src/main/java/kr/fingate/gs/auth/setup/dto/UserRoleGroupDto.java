package kr.fingate.gs.auth.setup.dto;

import kr.fingate.gs.auth.vo.UserRoleGroupVO;
import kr.fingate.gs.comon.annotation.ComparableEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
//@ComparableEntity("GA_USER_ROLE_GROUP")
@Alias("UserRoleGroupDto")
public class UserRoleGroupDto extends UserRoleGroupVO {


}
