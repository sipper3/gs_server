package kr.fingate.gs.sample;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController{
    @GetMapping("/test")
    public SampleDto test()  throws Exception {
        //long result = 1 / 0;
//        try{
//            long result = 1 / 0;
//        }catch (Exception e) {
//            e.printStackTrace();
//
//        }
        return SampleDto.builder()
                .id("1")
                .name("test")
                .build();
    }
}
