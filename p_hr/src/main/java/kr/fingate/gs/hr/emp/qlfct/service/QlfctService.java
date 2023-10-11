package kr.fingate.gs.hr.emp.qlfct.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.qlfct.dao.QlfctDao;
import kr.fingate.gs.hr.emp.qlfct.dao.QlfctModDao;
import kr.fingate.gs.hr.emp.qlfct.dto.QlfctDto;
import kr.fingate.gs.hr.vo.EmpQlfctVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class QlfctService {

    private final QlfctDao qlfctDao;
    private final QlfctModDao qlfctModDao;

    // 피고용인 자격정보조회
    public Page<QlfctDto> getQlfctList(@RequestBody EmpSearchDto empSearchDto) throws Exception {
        try {
            PageHelper.startPage(empSearchDto.getPageNum(), empSearchDto.getPageSize());
            return qlfctDao.getQlfctList(empSearchDto);
        } catch (BizException e) {
            log.error("QlfctService.getQlfctList BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            log.error("QlfctService.getQlfctList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 자격정보등록
    public void insQlfct(EmpQlfctVO params) throws Exception {
        try {
            qlfctModDao.insQlfct(params);
        } catch (Exception e) {
            log.error("QlfctService.insQlfct Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 자격정보수정
    public void updQlfct(EmpQlfctVO params) throws Exception {
        try {
            qlfctModDao.updQlfct(params);
        } catch (Exception e) {
            log.error("QlfctService.updQlfct Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 자격정보삭제
    public void delQlfct(EmpQlfctVO params) throws Exception {
        try {
            qlfctModDao.delQlfct(params);
        } catch (Exception e) {
            log.error("QlfctService.delQlfct Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }
}
