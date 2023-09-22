package kr.fingate.gs.auth.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsoLoginDto {

    private long clientNo;
    private String loginEmail;
    private String loginPswrd;

}
