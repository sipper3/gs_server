package kr.fingate.gs.sales.cntrc.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.sales.cntrc.dto.CntrcListDto;
import kr.fingate.gs.sales.cntrc.dto.CntrcListSearchDto;
import kr.fingate.gs.sales.cntrc.dto.SbscrListDto;
import kr.fingate.gs.sales.cntrc.dto.SbscrListSearchDto;
import kr.fingate.gs.sales.cntrc.service.CntrcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/cntrc")
@RestController
@RequiredArgsConstructor
public class CntrcController {

    final CntrcService cntrcService;

    @RequestMapping(value = "/getSbscrList", method = RequestMethod.POST)
    public PageInfoDto<SbscrListDto> getSbscrList(@RequestBody SbscrListSearchDto sbscrListSearchDto) throws Exception {
        return new PageInfoDto<>(cntrcService.getSbscrList(sbscrListSearchDto));
    }

    @RequestMapping(value = "/getCntrcList", method = RequestMethod.POST)
    public PageInfoDto<CntrcListDto> getCntrcList(@RequestBody CntrcListSearchDto cntrcListSearchDto) throws Exception {
        return new PageInfoDto<>(cntrcService.getCntrcList(cntrcListSearchDto));
    }
}
