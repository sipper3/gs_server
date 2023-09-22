package kr.fingate.gs.admin.code.controller;

import kr.fingate.gs.admin.code.service.CodeService;
import kr.fingate.gs.comon.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/${project.name}/api/code")
@RestController
@RequiredArgsConstructor
public class CodeController {
    final CodeService codeService;

    /**
     * (화면노출용) 코드 조회
     * @param codeInitList
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/selDispCode", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selDispCode(@RequestBody List<String> codeInitList) throws Exception {
        if(ObjectUtil.isEmpty(codeInitList)) return null;
        return codeService.selDispCode(codeInitList);
    }
}
