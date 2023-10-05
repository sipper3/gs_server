package kr.fingate.gs.auth.login.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
import kr.fingate.gs.auth.login.service.LoginService;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.common.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/sso")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/getCheckPswrd")
    public void getCheckPswrd(HttpServletResponse response, @RequestBody SsoLoginDto loginDto) throws Exception {

        if(ObjectUtil.isEmpty(loginDto.getLoginPswrd())){
            throw new BizException(ResponseCode.MISSING_PARAMETER);
        }

        loginService.getCheckPswad(loginDto);
    }

    @PostMapping("/updPswrd")
    public void updPswrd(@RequestBody SsoLoginDto loginDto) throws Exception {

        if(ObjectUtil.isEmpty(loginDto.getLoginPswrd()) || ObjectUtil.isEmpty(loginDto.getNewLoginPswrd())){
            throw new BizException(ResponseCode.MISSING_PARAMETER);
        }

        loginService.updPswrd(loginDto);
    }


}
