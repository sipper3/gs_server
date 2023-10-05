package kr.fingate.gs.auth.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.auth.login.dto.LoginDto;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.auth.login.service.LoginPublicService;
import kr.fingate.gs.comon.consts.CommonConst;
import kr.fingate.gs.comon.exception.BizError;
import kr.fingate.gs.comon.exception.BizException;
import kr.fingate.gs.comon.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/${project.name}/api/sso")
@RestController
@RequiredArgsConstructor
public class LoginPublicController {

    private final LoginPublicService loginPublicService;

    @PostMapping("/getLogin")
    public LoginDto getLogin(HttpServletResponse response, @RequestBody SsoLoginDto loginDto) throws Exception {

        if(loginDto.getClientNo() < CommonConst.FINPL_CLIENT_NO || ObjectUtil.isEmpty(loginDto.getLoginId()) || ObjectUtil.isEmpty(loginDto.getLoginPswrd())){
            throw new BizException(BizError.MISSING_PARAMETER);
        }

        return loginPublicService.getLogin(response, loginDto);
    }

    @PostMapping("/getToken")
    public Map<String, Object> getToken(HttpServletRequest request) throws Exception {
        return loginPublicService.getToken(request);
    }

}
