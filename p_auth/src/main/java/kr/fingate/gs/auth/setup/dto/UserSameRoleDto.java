package kr.fingate.gs.auth.setup.dto;

import kr.fingate.gs.auth.vo.UserVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("UserSameRoleDto")
public class UserSameRoleDto extends UserVO {


}
