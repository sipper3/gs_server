package kr.fingate.gs.auth.info.controller;

import kr.fingate.gs.common.dto.code.OrgnzDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/${project.name}/api/info/priv")
@RestController
@RequiredArgsConstructor
public class InfoPrivController {

    /**
     * 데이터열람조직 조회
     * @param systemCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getOrgnz/{systemCode}", method = RequestMethod.POST)
    public OrgnzDto getOrgnz(@PathVariable String systemCode) throws Exception {
        return new OrgnzDto();
    }
}
