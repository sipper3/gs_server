package kr.fingate.gs.auth.login.service;

import kr.fingate.gs.auth.login.dao.LoginDao;
import kr.fingate.gs.auth.login.dao.LoginModDao;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.core.aop.exception.BizException;
import kr.fingate.gs.core.aop.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginDao loginDao;
    private final LoginModDao loginModDao;


    public SsoLoginDto getCheckPswrd(SsoLoginDto loginDto) {

        SsoLoginDto ssoLoginDto = loginDao.getCheckPswrd(loginDto);

        if(ssoLoginDto == null){
            throw new BizException(ResponseCode.LOGN_NOT_USER);
        }

        if(ssoLoginDto.getLoginId().equals("")){
            throw new BizException(ResponseCode.LOGN_NOT_USER);
        }

        return ssoLoginDto;
    }

    public int updPswrd(SsoLoginDto loginDto) {

        int updCnt = loginModDao.updPswrd(loginDto);

        if(updCnt == 0){
            throw new BizException(ResponseCode.LOGN_NOT_USER);
        }

        return updCnt;
    }

    public int UpdLock(SsoLoginDto loginDto) {

        int updCnt = loginModDao.updLock(loginDto);

        if(updCnt == 0){
            throw new BizException(ResponseCode.LOGN_NOT_USER);
        }

        return updCnt;
    }
}
