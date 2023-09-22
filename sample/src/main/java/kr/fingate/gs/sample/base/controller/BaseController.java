package kr.fingate.gs.sample.base.controller;

import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.service.CoreService;
import kr.fingate.gs.sample.base.dto.SampleDto;
import kr.fingate.gs.sample.base.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/${project.name}/api/base")
@RestController
@RequiredArgsConstructor
public class BaseController {

    final BaseService baseService;
    @PostMapping("/test")
    public SampleDto test(@RequestBody Object object) throws Exception {
        return baseService.getSelectOne();
    }
    @PostMapping("/noreturn")
    public void noreturn(@RequestBody Object object) throws Exception {
    }
    @PostMapping("/token/noreturn")
    public void tokenNoreturn(@RequestBody Map<String, Object> map) throws Exception {
        for(String key : map.keySet()){
            System.out.println(String.format("%s:%s", key, String.valueOf(map.get(key))));
        }
    }
    @PostMapping("/token/norequest")
    public UserTokenDto tokenNorequest() throws Exception {
        return CoreService.getUserInfo();
    }
    @PostMapping("/notoken/path/{pathdata}")
    public Map<String, Object> pathPathData(@PathVariable String pathdata, @RequestBody UserTokenDto userTokenDto) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("clientNo", userTokenDto.getClientNo());
        map.put("userNo", userTokenDto.getUserNo()+100000);
        map.put("userName", userTokenDto.getUserName()+"456");
        map.put("pathdata", pathdata);
        return map;
    }
}
