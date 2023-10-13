package kr.fingate.gs.hr.emp.edu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.service.CoreService;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.edu.dao.EduDao;
import kr.fingate.gs.hr.emp.edu.dao.EduModDao;
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
    public Page<EmpEduVO> getEduList(@RequestBody EmpSearchDto params) throws Exception {
        try {
            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long clientNo = userTokenDto.getClientNo();
            params.setClientNo(clientNo);

            PageHelper.startPage(params.getPageNum(), params.getPageSize());
            return eduDao.getEduList(params);

        } catch (Exception e) {
            log.error("EduService.getEduList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 교육정보등록
    public void insEdu(EmpEduVO params) throws Exception {
        try {
            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long clientNo = userTokenDto.getClientNo();
            long tokenUserNo = userTokenDto.getUserNo();
            params.setClientNo(clientNo);
            params.setRegUserNo(tokenUserNo);

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
