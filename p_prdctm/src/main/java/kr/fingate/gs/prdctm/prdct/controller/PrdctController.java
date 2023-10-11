package kr.fingate.gs.prdctm.prdct.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.PrdctInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.SearchPrdctDto;
import kr.fingate.gs.prdctm.prdct.service.PrdctService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/prdct")
@RestController
@RequiredArgsConstructor
public class PrdctController {

    final PrdctService prdctService;

    @RequestMapping(value="/getPrdctInfoList", method={RequestMethod.POST})
    public PageInfoDto<PrdctInfoDto> getPrdctInfoList(@RequestBody SearchPrdctDto searchPrdctDto) throws Exception {
        return new PageInfoDto<>(prdctService.getPrdctInfoList(searchPrdctDto));
    }

    @RequestMapping(value="/getPrdctInfo", method={RequestMethod.POST})
    public PrdctInfoDto getPrdctInfo(@RequestBody SearchPrdctDto searchPrdctDto) throws Exception {
        return prdctService.getPrdctInfo(searchPrdctDto);
    }

}
