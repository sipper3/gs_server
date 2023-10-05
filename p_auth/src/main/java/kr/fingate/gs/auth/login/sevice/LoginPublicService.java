package kr.fingate.gs.auth.login.sevice;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.auth.login.dao.LoginDao;
import kr.fingate.gs.auth.login.dto.LoginDto;
import kr.fingate.gs.auth.login.dto.LoginInfoDto;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.comon.consts.enums.RedisKey;
import kr.fingate.gs.comon.exception.BizError;
import kr.fingate.gs.comon.exception.BizException;
import kr.fingate.gs.comon.util.ObjectUtil;
import kr.fingate.gs.comon.util.RedisUtil;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.security.service.JwtService;
import kr.fingate.gs.core.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginPublicService {

    @Value("${jwt.secretKey}")
    private String encryptionKey;

    final RedisUtil redisUtil;

    private final JwtService jwtService;
    final LoginService loginService;

    private final LoginDao loginDao;

    public LoginDto getLogin(HttpServletResponse response, SsoLoginDto ssoLoginDto) throws Exception {

        LoginInfoDto loginInfoDto = loginDao.getLogin(ssoLoginDto);

        if(loginInfoDto == null){
            throw new BizException(BizError.LOGN_NOT_USER);
        }

        if(loginInfoDto.getLockYn().equals("Y")){
            throw new BizException(BizError.LOGN_LOCK_USER);
        }

        if(loginInfoDto.getExpiredYn().equals("Y")){
            throw new BizException(BizError.LOGN_EXPIRE_USER);
        }

        String fg = EncryptionUtil.aesEncode(ssoLoginDto.getClientNo() + ":" + ssoLoginDto.getLoginId(), encryptionKey);

        LoginDto loginDto = new LoginDto();
        loginDto.setLoginId(ssoLoginDto.getLoginId());
        loginDto.setFg(fg);


        return loginDto;
    }


    public String cookieUser(HttpServletRequest request) throws Exception {

        String cookieName = "_fg";
        String cookieValue = null;

        Cookie[] cookies = request.getCookies(); // 모든 쿠키 가져오기

        if(cookies != null){
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                log.error(c.getName());
                log.error(c.getValue());
                if (name.equals(cookieName)) {
                    cookieValue = value;
                }
            }
        }

        return cookieValue;
    }

    public UserTokenDto getSessionUser(HttpServletRequest request) throws Exception {

        UserTokenDto result = new UserTokenDto();
        try {

            String cookieValue = cookieUser(request);

            if (cookieValue == null) {
                throw new Exception("need to login(loginid is null)");
            }

            // clientNo : loginId
            String decodeStr = EncryptionUtil.aesDecode(cookieValue, encryptionKey);
            String[] info = decodeStr.split(":");
            result.setClientNo(Long.parseLong(info[0]));
            result.setLoginId(info[1]);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }

        return result;
    }

    public Map<String, Object> getToken(HttpServletRequest request) throws Exception {

        String result = null;
        LoginInfoDto loginInfoDto = null;
        UserTokenDto userTokenDto = getSessionUser(request);

        try {
            loginInfoDto = redisUtil.getData(redisUtil.getKey(userTokenDto.getClientNo(), RedisKey.GS_USER_INFO, userTokenDto.getLoginId(), "info", String.valueOf(userTokenDto.getClientNo())), LoginInfoDto.class);
        } catch(Exception e) {

        }

        if(ObjectUtil.isEmpty(loginInfoDto)){
            try {
                loginInfoDto = loginDao.getLoginUserInfo(userTokenDto);
            } catch(Exception e) {
                throw new AccessDeniedException("사용자 정보를 찾을 수 없습니다.");
            }
        }

        if(ObjectUtil.isEmpty(loginInfoDto)){
            throw new AccessDeniedException("사용자 정보를 찾을 수 없습니다.");
        }

        Map<String, Object> returnMap = new HashMap<>();
        String accessToken = jwtService.createAccessToken(loginInfoDto);
        returnMap.put("accessToken", accessToken);

        return returnMap;

    }

}
