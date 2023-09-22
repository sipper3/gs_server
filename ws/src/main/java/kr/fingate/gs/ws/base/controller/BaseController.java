package kr.fingate.gs.ws.base.controller;

import kr.fingate.gs.ws.base.dto.WsDto;
import kr.fingate.gs.ws.base.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/base")
@RestController
@RequiredArgsConstructor
public class BaseController {

    final BaseService baseService;
    @PostMapping("/test")
    public WsDto test(@RequestBody Object object) throws Exception {
        //long result = 1 / 0;
//        try{
//            long result = 1 / 0;
//        }catch (Exception e) {
//            e.printStackTrace();
//
//        }
        return baseService.getSelectOne();
    }
}
