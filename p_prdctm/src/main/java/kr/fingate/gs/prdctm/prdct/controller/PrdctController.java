package kr.fingate.gs.prdctm.prdct.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.PrdctInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.RprsnPrdctDto;
import kr.fingate.gs.prdctm.prdct.dto.SearchPrdctDto;
import kr.fingate.gs.prdctm.prdct.service.PrdctService;
import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/${project.name}/api/item")
@RestController
@RequiredArgsConstructor
public class PrdctController {

    final PrdctService prdctService;

    @RequestMapping(value="/getPrdctInfoList", method={RequestMethod.POST})
    PageInfoDto<PrdctInfoDto> getPrdctInfoList(@RequestBody SearchPrdctDto searchPrdctDto) throws Exception {
        return new PageInfoDto<>(prdctService.getPrdctInfoList(searchPrdctDto));
    }

    @RequestMapping(value="/getRprsnPrdctList", method={RequestMethod.POST})
    PageInfoDto<RprsnPrdctDto> getRprsnPrdctList(@RequestBody SearchPrdctDto searchPrdctDto) throws Exception {
        return new PageInfoDto<>(prdctService.getRprsnPrdctList(searchPrdctDto));
    }

    @RequestMapping(value="/insRprsnPrdt", method={RequestMethod.POST})
    public Map<String, Long> insRprsnPrdt(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {

        Map<String, Long> rtnMap = new HashMap<>();
        rtnMap.put("rprsnPrdtNo", prdctService.insRprsnPrdt(rprsnPrdctVO));
        return rtnMap;

    }

    @RequestMapping(value="/updRprsnPrdt", method={RequestMethod.POST})
    public void updRprsnPrdt(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {

        prdctService.updRprsnPrdt(rprsnPrdctVO);

    }


}
