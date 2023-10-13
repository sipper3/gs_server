package kr.fingate.gs.prdctm.rprsn.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.prdctm.rprsn.dto.RprsnPrdctDto;
import kr.fingate.gs.prdctm.rprsn.dto.SearchRprsnPrdctDto;
import kr.fingate.gs.prdctm.rprsn.service.RprsnPrdctService;
import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/${project.name}/api/rprsn")
@RestController
@RequiredArgsConstructor
public class RprsnPrdctController {

    final RprsnPrdctService rprsnPrdctService;
    @RequestMapping(value="/getRprsnPrdctList", method={RequestMethod.POST})
    public PageInfoDto<RprsnPrdctDto> getRprsnPrdctList(@RequestBody SearchRprsnPrdctDto searchPrdctDto) throws Exception {
        return new PageInfoDto<>(rprsnPrdctService.getRprsnPrdctList(searchPrdctDto));
    }

    @RequestMapping(value="/getRprsnPrdct", method={RequestMethod.POST})
    public RprsnPrdctDto getRprsnPrdct(@RequestBody SearchRprsnPrdctDto searchPrdctDto) throws Exception {
        return rprsnPrdctService.getRprsnPrdct(searchPrdctDto);
    }

    @RequestMapping(value="/insRprsnPrdct", method={RequestMethod.POST})
    public Map<String, Long> insRprsnPrdct(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {
        Map<String, Long> rtnMap = new HashMap<>();
        rtnMap.put("rprsnPrdctNo", rprsnPrdctService.insRprsnPrdct(rprsnPrdctVO));
        return rtnMap;
    }

    @RequestMapping(value="/updRprsnPrdct", method={RequestMethod.POST})
    public void updRprsnPrdct(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {
        rprsnPrdctService.updRprsnPrdct(rprsnPrdctVO);
    }

    @RequestMapping(value="/delRprsnPrdct", method={RequestMethod.POST})
    public void delRprsnPrdct(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {
        rprsnPrdctService.delRprsnPrdct(rprsnPrdctVO);
    }


}
