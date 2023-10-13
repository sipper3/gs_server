package kr.fingate.gs.auth.login.dao;

import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginModDao {

    int updPswrd(SsoLoginDto loginDto);
    int updLock(SsoLoginDto loginDto);

}
