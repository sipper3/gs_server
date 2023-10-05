package kr.fingate.gs.auth.login.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.auth.login.dao.SsoLoginDao;
import kr.fingate.gs.core.consts.enums.TokenType;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.security.service.JwtService;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SsoLoginService {

    private final JwtService jwtService;
    private final SsoLoginDao ssoLoginDao;

    public Map<String, Object> getLogin(HttpServletResponse response, SsoLoginDto loginDto) throws Exception {
        // 비밀번호 조회
        SsoLoginDto account = ssoLoginDao.getLogin(loginDto);

        if(account == null || !PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(loginDto.getLoginPswrd(), account.getLoginPswrd())){
            throw new Exception("로그인 실패! 이메일 또는 비밀번호를 확인해주세요.");
        }

        UserTokenDto userTokenDto = ssoLoginDao.getToken(loginDto);
        Map<String, Object> returnMap = new HashMap<>();
        String accessToken = jwtService.createAccessToken(userTokenDto);
        returnMap.put("accessToken", accessToken);

        response.setHeader(TokenType.ACCESS_TOKEN.getHeader(), accessToken);

        return returnMap;
    }
}
