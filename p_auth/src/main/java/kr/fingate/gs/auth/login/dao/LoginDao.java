package kr.fingate.gs.auth.login.dao;

import kr.fingate.gs.auth.login.dto.LoginInfoDto;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {

    LoginInfoDto getLogin(SsoLoginDto ssoLoginDto);
    LoginInfoDto getLoginUserInfo(UserTokenDto userTokenDto);
    SsoLoginDto getCheckPswrd(SsoLoginDto loginDto);

}
