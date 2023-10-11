package kr.fingate.gs.hr.emp.edu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.edu.dao.EduDao;
import kr.fingate.gs.hr.emp.edu.dao.EduModDao;
import kr.fingate.gs.hr.emp.edu.dto.EduDto;
import kr.fingate.gs.hr.vo.EmpEduVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class EduService {

    private final EduDao eduDao;
    private final EduModDao eduModDao;

    // 피고용인 교육정보조회
    public Page<EduDto> getEduList(@RequestBody EmpSearchDto empSearchDto) throws Exception {
        try {
            PageHelper.startPage(empSearchDto.getPageNum(), empSearchDto.getPageSize());
            return eduDao.getEduList(empSearchDto);
        } catch (BizException e) {
            log.error("EduService.getEduList BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            log.error("EduService.getEduList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 교육정보등록
    public void insEdu(EmpEduVO params) throws Exception {
        try {
            eduModDao.insEdu(params);
        } catch (Exception e) {
            log.error("EduService.insEdu Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 교육정보수정
    public void updEdu(EmpEduVO params) throws Exception {
        try {
            eduModDao.updEdu(params);
        } catch (Exception e) {
            log.error("EduService.updEdu Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 교육정보삭제
    public void delEdu(EmpEduVO params) throws Exception {
        try {
            eduModDao.delEdu(params);
        } catch (Exception e) {
            log.error("EduService.delEdu Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }
}
