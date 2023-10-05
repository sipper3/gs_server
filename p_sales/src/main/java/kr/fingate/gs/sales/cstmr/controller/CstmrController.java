package kr.fingate.gs.sales.cstmr.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.sales.cstmr.dto.CstmrListDto;
import kr.fingate.gs.sales.cstmr.dto.CstmrListSearchDto;
import kr.fingate.gs.sales.cstmr.service.CstmrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/cstmr")
@RestController
@RequiredArgsConstructor
public class CstmrController {

    final CstmrService cstmrService;

    /**
     *
     * @param cstmrListSearchDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCstmrList", method = RequestMethod.POST)
    public PageInfoDto<CstmrListDto> getCnslList(@RequestBody CstmrListSearchDto cstmrListSearchDto) throws Exception {
        return new PageInfoDto<>(cstmrService.getCstmrList(cstmrListSearchDto));
    }
}
