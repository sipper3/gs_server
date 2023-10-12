package kr.fingate.gs.hr.emp.memo.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.memo.dto.MemoDto;
import kr.fingate.gs.hr.emp.memo.service.MemoService;
import kr.fingate.gs.hr.vo.EmpMemoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/emp/memo")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemoController {
    final MemoService memoService;

    /**
     * 피고용인 메모정보조회
     * @param params
     * @return PageInfo<MemoDto>
     * @throws Exception
     */
    @RequestMapping(value = "/getMemoList", method = RequestMethod.POST)
    public PageInfoDto<MemoDto> getMemoList(@RequestBody EmpSearchDto params) throws Exception {
        return new PageInfoDto<MemoDto>(memoService.getMemoList(params));
    }

    /**
     * 피고용인 메모등록
     * @param params
     * @return PageInfo<MemoDto>
     * @throws Exception
     */
    @RequestMapping(value = "/insMemo", method = RequestMethod.POST)
    public void insMemo(@RequestBody EmpMemoVO params) throws Exception {
        memoService.insMemo(params);
    }

    /**
     * 피고용인 메모수정
     * @param params
     * @return PageInfo<MemoDto>
     * @throws Exception
     */
    @RequestMapping(value = "/updMemo", method = RequestMethod.POST)
    public void updMemo(@RequestBody EmpMemoVO params) throws Exception {
        memoService.updMemo(params);
    }

    /**
     * 피고용인 메모삭제
     * @param params
     * @return PageInfo<MemoDto>
     * @throws Exception
     */
    @RequestMapping(value = "/delMemo", method = RequestMethod.POST)
    public void delMemo(@RequestBody EmpMemoVO params) throws Exception {
        memoService.delMemo(params);
    }
}
