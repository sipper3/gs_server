package kr.fingate.gs.auth.signup.dto;

import kr.fingate.gs.core.consts.enums.AuthmRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    
    /* - 자체 회원가입 - */

    private long clientNo;

    private long userNo;

    private String loginEmail;

    private String loginPswrd;

    private String userName;

    private String userNiknm;

    private String cellTellNo;

    private String birthDate;

    private String genderType;

    private String authmEmailYn;

    private String authmTellnoYn;

    /* - Oauth2 회원가입 - */

    private String socialType;

    private String socialId;

    private AuthmRole authmRole;

}
