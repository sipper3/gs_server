package kr.fingate.gs.auth.signup.dao;

import kr.fingate.gs.auth.signup.dto.SignupDto;

import java.util.Map;

public interface SignupDao {

    boolean getOvlap(Map<String, Object> params);

    void insUser(SignupDto signupDto);
    void insUserAuthmMngmt(SignupDto signupDto);

}
