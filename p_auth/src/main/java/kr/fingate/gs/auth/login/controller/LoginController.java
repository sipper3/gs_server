package kr.fingate.gs.auth.login.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.auth.login.sevice.LoginService;
import kr.fingate.gs.comon.consts.CommonConst;
import kr.fingate.gs.comon.exception.BizError;
import kr.fingate.gs.comon.exception.BizException;
import kr.fingate.gs.comon.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/${project.name}/api/sso")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/getCheckPswrd")
    public void getCheckPswrd(HttpServletResponse response, @RequestBody SsoLoginDto loginDto) throws Exception {

        if(ObjectUtil.isEmpty(loginDto.getLoginPswrd())){
            throw new BizException(BizError.MISSING_PARAMETER);
        }

        loginService.getCheckPswad(loginDto);
    }

    @PostMapping("/updPswrd")
    public void updPswrd(@RequestBody SsoLoginDto loginDto) throws Exception {

        if(ObjectUtil.isEmpty(loginDto.getLoginPswrd()) || ObjectUtil.isEmpty(loginDto.getNewLoginPswrd())){
            throw new BizException(BizError.MISSING_PARAMETER);
        }

        loginService.updPswrd(loginDto);
    }


}
