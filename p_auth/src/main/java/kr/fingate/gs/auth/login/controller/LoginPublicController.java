package kr.fingate.gs.auth.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.auth.login.dto.LoginDto;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.auth.login.service.LoginPublicService;
import kr.fingate.gs.core.aop.exception.BizException;
import kr.fingate.gs.core.aop.response.ResponseCode;
import kr.fingate.gs.common.consts.CommonConst;
import kr.fingate.gs.common.util.ObjectUtil;
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
public class LoginPublicController {

    private final LoginPublicService loginPublicService;

    @PostMapping("/getLogin")
    public LoginDto getLogin(HttpServletResponse response, @RequestBody SsoLoginDto loginDto) throws Exception {

        if(loginDto.getClientNo() < CommonConst.FINPL_CLIENT_NO || ObjectUtil.isEmpty(loginDto.getLoginId()) || ObjectUtil.isEmpty(loginDto.getLoginPswrd())){
            throw new BizException(ResponseCode.MISSING_PARAMETER);
        }

        return loginPublicService.getLogin(response, loginDto);
    }

    @PostMapping("/getToken")
    public Map<String, Object> getToken(HttpServletRequest request) throws Exception {
        return loginPublicService.getToken(request);
    }

    @PostMapping("/getAccessUser")
    public UserTokenDto getAccessUser() throws Exception {
        return CoreService.getUserInfo();
    }
}
