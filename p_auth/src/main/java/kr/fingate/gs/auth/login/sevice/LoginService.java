package kr.fingate.gs.auth.login.sevice;

import kr.fingate.gs.auth.login.dao.LoginDao;
import kr.fingate.gs.auth.login.dto.LoginDto;
import kr.fingate.gs.auth.login.dto.LoginInfoDto;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.comon.exception.BizError;
import kr.fingate.gs.comon.exception.BizException;
import kr.fingate.gs.core.security.service.JwtService;
import kr.fingate.gs.core.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

//    private final JwtService jwtService;
    private final LoginDao loginDao;


    public void getCheckPswad(SsoLoginDto loginDto) {

        SsoLoginDto ssoLoginDto = loginDao.getCheckPswad(loginDto);

        if(ssoLoginDto == null){
            throw new BizException(BizError.LOGN_NOT_USER);
        }

        if(ssoLoginDto.getLoginId().equals("")){
            throw new BizException(BizError.LOGN_NOT_USER);
        }

    }

    public void updPswrd(SsoLoginDto loginDto) {

        SsoLoginDto ssoLoginDto = loginDao.updPswrd(loginDto);

        if(ssoLoginDto == null){
            throw new BizException(BizError.LOGN_NOT_USER);
        }

        if(ssoLoginDto.getLoginId().equals("")){
            throw new BizException(BizError.LOGN_NOT_USER);
        }

    }

    public void UpdLock(SsoLoginDto loginDto) {

        SsoLoginDto ssoLoginDto = loginDao.updLock(loginDto);

        if(ssoLoginDto == null){
            throw new BizException(BizError.LOGN_NOT_USER);
        }

        if(ssoLoginDto.getLoginId().equals("")){
            throw new BizException(BizError.LOGN_NOT_USER);
        }

    }
}
