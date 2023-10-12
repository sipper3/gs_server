package kr.fingate.gs.cmsn.prdct.controller;

import kr.fingate.gs.cmsn.prdct.dto.*;
import kr.fingate.gs.cmsn.prdct.service.PrdctService;
import kr.fingate.gs.common.dto.PageInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 상품율 관리
@RequestMapping("/${project.name}/api/prdct")
@RestController
@RequiredArgsConstructor
public class PrdctController {

    final PrdctService prdctService;

    /**
     * 대표상품 리스트
     * @param searchRprsnPrdctDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getRprsnPrdctList", method=RequestMethod.POST)
    @ResponseBody
    public PageInfoDto<RprsnPrdctDto> getRprsnPrdctList(@RequestBody SearchRprsnPrdctDto searchRprsnPrdctDto) throws Exception {
        return new PageInfoDto<>(prdctService.getRprsnPrdctList(searchRprsnPrdctDto));
    }

    /**
     * 상품율 리스트
     * @param searchPrdctRateMngmnDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getPrdctRateList", method= RequestMethod.POST)
    public List<PrdctRateMngmnDto> getPrdctRateMngmnList(@RequestBody SearchPrdctRateMngmnDto searchPrdctRateMngmnDto)  throws Exception {
        return prdctService.getPrdctRateMngmnList(searchPrdctRateMngmnDto);
    }

    /**
     * 상품율관리 상세
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getPrdctRateDtails", method=RequestMethod.POST)
    public PrdtRateDtalDto getPrdctRateDtails(@RequestBody SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctService.getPrdctRateDtails(searchPrdctRateSeqDto);
    }

    /**
     * 상품율관리 상세 저장
      * @param params
     * @return
     * @throws Exception
     */
//    @RequestMapping(value="/insPrdctRate", method=RequestMethod.POST)
//    public PrdtRateMgmtModel insPrdctRateDtails(@RequestBody PrdtMgmtSaveModel params)  throws Exception {
//        PrdtRateMgmtModel rateMgmt = prdtMgmtService.insPrdtRateMgmtDtal(params);
//        return rateMgmt;
//    }

}
