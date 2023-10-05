package kr.fingate.gs.auth.login.dao;

import kr.fingate.gs.auth.login.dto.LoginInfoDto;
import kr.fingate.gs.auth.user.dto.UserDto;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {

    LoginInfoDto getLogin(SsoLoginDto ssoLoginDto);
    LoginInfoDto getLoginUserInfo(UserTokenDto userTokenDto);
    SsoLoginDto getCheckPswad(SsoLoginDto loginDto);
    SsoLoginDto updPswrd(SsoLoginDto loginDto);
    SsoLoginDto updLock(SsoLoginDto loginDto);

}
