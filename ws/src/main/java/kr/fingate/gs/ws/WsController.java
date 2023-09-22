package kr.fingate.gs.ws;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WsController{
    @GetMapping("/test")
    public WsDto test()  throws Exception {
        //long result = 1 / 0;
//        try{
//            long result = 1 / 0;
//        }catch (Exception e) {
//            e.printStackTrace();
//
//        }
        return WsDto.builder()
                .id("1")
                .name("test")
                .build();
    }
}
