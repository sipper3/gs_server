package kr.fingate.gs.hr.emp.edu.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.edu.dto.EduDto;
import kr.fingate.gs.hr.emp.edu.service.EduService;
import kr.fingate.gs.hr.vo.EmpEduVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/emp/edu")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EduController {
    final EduService eduService;

    /**
     * 피고용인 교육정보조회
     * @param params
     * @return PageInfo<EduDto>
     * @throws Exception
     */
    @RequestMapping(value = "/getEduList", method = RequestMethod.POST)
    public PageInfoDto<EduDto> getEduList(@RequestBody EmpSearchDto params) throws Exception {
        return new PageInfoDto<EduDto>(eduService.getEduList(params));
    }

    /**
     * 피고용인 교육등록
     * @param params
     * @return PageInfo<EduDto>
     * @throws Exception
     */
    @RequestMapping(value = "/insEdu", method = RequestMethod.POST)
    public void insEdu(@RequestBody EmpEduVO params) throws Exception {
        eduService.insEdu(params);
    }

    /**
     * 피고용인 교육수정
     * @param params
     * @return PageInfo<EduDto>
     * @throws Exception
     */
    @RequestMapping(value = "/updEdu", method = RequestMethod.POST)
    public void updEdu(@RequestBody EmpEduVO params) throws Exception {
        eduService.updEdu(params);
    }

    /**
     * 피고용인 교육삭제
     * @param params
     * @return PageInfo<EduDto>
     * @throws Exception
     */
    @RequestMapping(value = "/delEdu", method = RequestMethod.POST)
    public void delEdu(@RequestBody EmpEduVO params) throws Exception {
        eduService.delEdu(params);
    }
}
