package kr.fingate.gs.hr.emp.family.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.family.dao.FamilyDao;
import kr.fingate.gs.hr.emp.family.dao.FamilyModDao;
import kr.fingate.gs.hr.emp.family.dto.FamilyDto;
import kr.fingate.gs.hr.vo.EmpFamilyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyDao familyDao;
    private final FamilyModDao familyModDao;

    // 피고용인 가족정보조회 - 항목 전체 리스트
    public Page<FamilyDto> getFamilyList(@RequestBody EmpSearchDto empSearchDto) throws Exception {
        try {
            PageHelper.startPage(empSearchDto.getPageNum(), empSearchDto.getPageSize());
            return familyDao.getFamilyList(empSearchDto);
        } catch (BizException e) {
            log.error("FamilyService.getFamilyList BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            log.error("FamilyService.getFamilyList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 가족정보등록
    public void insFamily(EmpFamilyVO params) throws Exception {
        try {
            familyModDao.insFamily(params);
        } catch (Exception e) {
            log.error("FamilyService.insFamily Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 가족정보수정
    public void updFamily(EmpFamilyVO params) throws Exception {
        try {
            familyModDao.updFamily(params);
        } catch (Exception e) {
            log.error("FamilyService.updFamily Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 가족정보삭제
    public void delFamily(EmpFamilyVO params) throws Exception {
        try {
            familyModDao.delFamily(params);
        } catch (Exception e) {
            log.error("FamilyService.delFamily Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }
}
