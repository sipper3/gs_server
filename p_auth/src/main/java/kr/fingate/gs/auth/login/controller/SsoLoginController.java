package kr.fingate.gs.auth.login.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.auth.login.sevice.SsoLoginService;
import kr.fingate.gs.comon.consts.CommonConst;
import kr.fingate.gs.comon.util.ObjectUtil;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.service.CoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/${project.name}/api/sso")
@RestController
@RequiredArgsConstructor
public class SsoLoginController {

    private final SsoLoginService ssoLoginService;

    @PostMapping("/login")
    public Map<String, Object> getLogin(HttpServletResponse response, @RequestBody SsoLoginDto loginDto) throws Exception {
        if(loginDto.getClientNo() < CommonConst.FINPL_CLIENT_NO){
            throw new Exception("clientNo is null!");
        }
        if(ObjectUtil.isEmpty(loginDto.getLoginId())){
            throw new Exception("loginEmail is null!");
        }
        if(ObjectUtil.isEmpty(loginDto.getLoginPswrd())){
            throw new Exception("loginPswrd is null!");
        }

        return ssoLoginService.getLogin(response, loginDto);
    }

    @PostMapping("/get/user")
    public UserTokenDto getUser() throws Exception {
        return CoreService.getUserInfo();
    }
}
