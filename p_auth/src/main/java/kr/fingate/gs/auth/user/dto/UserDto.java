package kr.fingate.gs.auth.user.dto;

import kr.fingate.gs.auth.vo.UserVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends UserVO {

    // 역할마지막수정일
    private String roleLastModDate;

    // 역할마지막수정자
    private long roleLastModUser;

    // 조직명
    private String orgnzName;

}
