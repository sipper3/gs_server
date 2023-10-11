package kr.fingate.gs.hr.emp.qlfct.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.hr.emp.qlfct.dto.QlfctDto;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.qlfct.service.QlfctService;
import kr.fingate.gs.hr.vo.EmpQlfctVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/emp/qlfct")
@RestController
@RequiredArgsConstructor
@Slf4j
public class QlfctController {
    final QlfctService empQlfctService;

    /**
     * 피고용인 자격정보조회
     * @param params
     * @return PageInfo<EmpQlfctDto>
     * @throws Exception
     */
    @RequestMapping(value = "/getQlfctList", method = RequestMethod.POST)
    public PageInfoDto<QlfctDto> getQlfctList(@RequestBody EmpSearchDto params) throws Exception {
        return new PageInfoDto<QlfctDto>(empQlfctService.getQlfctList(params));
    }

    /**
     * 피고용인 자격등록
     * @param params
     * @return PageInfo<EmpQlfctDto>
     * @throws Exception
     */
    @RequestMapping(value = "/insQlfct", method = RequestMethod.POST)
    public void insQlfct(@RequestBody EmpQlfctVO params) throws Exception {
        empQlfctService.insQlfct(params);
    }

    /**
     * 피고용인 자격수정
     * @param params
     * @return PageInfo<EmpQlfctDto>
     * @throws Exception
     */
    @RequestMapping(value = "/updQlfct", method = RequestMethod.POST)
    public void updQlfct(@RequestBody EmpQlfctVO params) throws Exception {
        empQlfctService.updQlfct(params);
    }

    /**
     * 피고용인 자격삭제
     * @param params
     * @return PageInfo<EmpQlfctDto>
     * @throws Exception
     */
    @RequestMapping(value = "/delQlfct", method = RequestMethod.POST)
    public void delQlfct(@RequestBody EmpQlfctVO params) throws Exception {
        empQlfctService.delQlfct(params);
    }
}
