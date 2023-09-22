package kr.fingate.gs.auth.signup.service;

import kr.fingate.gs.auth.signup.dao.SignupDao;
import kr.fingate.gs.auth.signup.dto.SignupDto;
import kr.fingate.gs.comon.consts.enums.SocialType;
import kr.fingate.gs.comon.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class SignupService {

    private final SignupDao signupDao;

    // 회원가입 시 중복체크
    public void getOvlap(String atrvt, SignupDto signupDto) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("atrvt", ObjectUtil.defaultStr(atrvt, ""));
        params.put("user", signupDto);

        if(!signupDao.getOvlap(params)){
            throw new Exception("중복 발생");
        }
    }

    // 회원가입 진행
    public void insUser(SocialType socialType, SignupDto signupDto) throws Exception {
        // 회원가입이 가능한지 전체 항목에 대해 재검사
        this.getOvlap(null, signupDto);

        signupDto.setLoginPswrd(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(signupDto.getLoginPswrd()));
        signupDto.setSocialType(socialType.name());

        signupDao.insUser(signupDto);
        signupDao.insUserAuthmMngmt(signupDto);
    }
}
