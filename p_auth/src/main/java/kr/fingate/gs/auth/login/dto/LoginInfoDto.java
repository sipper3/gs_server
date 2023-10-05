package kr.fingate.gs.auth.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import lombok.*;

import java.util.Date;

@Setter
@Getter
public class LoginInfoDto extends UserTokenDto {

    private String orgnzName;
    private String orgnzDtlName;
    private String pstnCode;
    private String dutyCode;
    private String bsnsCode;

    private String lockYn;

    @JsonIgnore
    private String expiredYn;

}
