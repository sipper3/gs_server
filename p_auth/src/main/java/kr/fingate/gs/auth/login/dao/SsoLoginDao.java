package kr.fingate.gs.auth.login.dao;

import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;

public interface SsoLoginDao {

    SsoLoginDto getLogin(SsoLoginDto ssoLoginDto);
    UserTokenDto getToken(SsoLoginDto ssoLoginDto);

}
