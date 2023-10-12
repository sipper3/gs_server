package kr.fingate.gs.auth.login.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String loginId;
    private String fg;
    private String token;
    private String expiredYn;

}
