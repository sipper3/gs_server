package kr.fingate.gs.hr.emp.family.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.hr.emp.family.dto.FamilyDto;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.family.service.FamilyService;
import kr.fingate.gs.hr.vo.EmpFamilyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/emp/family")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FamilyController {
    final FamilyService familyService;

    /**
     * 피고용인 가족조회
     * @param params
     * @return PageInfo<FamilyDto>
     * @throws Exception
     */
    @RequestMapping(value = "/getFamilyList", method = RequestMethod.POST)
    public PageInfoDto<FamilyDto> getFamilyList(@RequestBody EmpSearchDto params) throws Exception {
        return new PageInfoDto<FamilyDto>(familyService.getFamilyList(params));
    }

    /**
     * 피고용인 가족등록
     * @param params
     * @return PageInfo<FamilyDto>
     * @throws Exception
     */
    @RequestMapping(value = "/insFamily", method = RequestMethod.POST)
    public void insFamily(@RequestBody EmpFamilyVO params) throws Exception {
        familyService.insFamily(params);
    }

    /**
     * 피고용인 가족수정
     * @param params
     * @return PageInfo<FamilyDto>
     * @throws Exception
     */
    @RequestMapping(value = "/updFamily", method = RequestMethod.POST)
    public void updFamily(@RequestBody EmpFamilyVO params) throws Exception {
        familyService.updFamily(params);
    }

    /**
     * 피고용인 가족삭제
     * @param params
     * @return PageInfo<FamilyDto>
     * @throws Exception
     */
    @RequestMapping(value = "/delFamily", method = RequestMethod.POST)
    public void delFamily(@RequestBody EmpFamilyVO params) throws Exception {
        familyService.delFamily(params);
    }
}
