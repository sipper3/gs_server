package kr.fingate.gs.auth.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import lombok.Data;

@Data
public class LoginDto {

    private String loginId;
    private String fg;
    private String token;

}
