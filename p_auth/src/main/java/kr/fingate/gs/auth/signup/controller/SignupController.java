//package kr.fingate.gs.auth.signup.controller;
//
//import kr.fingate.gs.auth.signup.dto.SignupDto;
//import kr.fingate.gs.auth.signup.service.SignupService;
//import kr.fingate.gs.common.consts.CommonConst;
//import kr.fingate.gs.common.consts.enums.SocialType;
//import kr.fingate.gs.core.consts.enums.AuthmRole;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RequestMapping("/${project.name}/api/signup")
//@RestController
//@RequiredArgsConstructor
//public class SignupController {
//
//    private final SignupService signupService;
//
//    @PostMapping("/get/ovlap/{atrvt:.+}")
//    public void getOvlap(@PathVariable String atrvt, @RequestBody SignupDto signupDto) throws Exception {
//        if(signupDto.getClientNo() < CommonConst.FINPL_CLIENT_NO){
//            throw new Exception("clientNo is null!");
//        }
//
//        signupService.getOvlap(atrvt, signupDto);
//    }
//
//    @PostMapping("/ins")
//    public void insUser(@RequestBody SignupDto signupDto) throws Exception {
//        if(signupDto.getClientNo() < CommonConst.FINPL_CLIENT_NO){
//            throw new Exception("clientNo is null!");
//        }
//
//        // 자체 회원가입
//        signupDto.setAuthmRole(AuthmRole.USER);
//        signupService.insUser(SocialType.IGATE, signupDto);
//    }
//}
