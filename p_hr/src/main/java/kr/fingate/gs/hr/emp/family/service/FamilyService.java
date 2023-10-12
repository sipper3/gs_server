package kr.fingate.gs.hr.emp.family.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.service.CoreService;
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
    public Page<FamilyDto> getFamilyList(@RequestBody EmpSearchDto params) throws Exception {
        try {
            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long clientNo = userTokenDto.getClientNo();
            params.setClientNo(clientNo);

            PageHelper.startPage(params.getPageNum(), params.getPageSize());
            return familyDao.getFamilyList(params);

        } catch (Exception e) {
            log.error("FamilyService.getFamilyList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 가족정보등록
    public void insFamily(EmpFamilyVO params) throws Exception {
        try {
            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long clientNo = userTokenDto.getClientNo();
            long tokenUserNo = userTokenDto.getUserNo();
            params.setClientNo(clientNo);
            params.setRegUserNo(tokenUserNo);
            params.setModUserNo(tokenUserNo);

            familyModDao.insFamily(params);
        } catch (Exception e) {
            log.error("FamilyService.insFamily Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 가족정보수정
    public void updFamily(EmpFamilyVO params) throws Exception {
        try {
            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long tokenUserNo = userTokenDto.getUserNo();
            params.setModUserNo(tokenUserNo);

            familyModDao.updFamily(params);
        } catch (Exception e) {
            log.error("FamilyService.updFamily Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 가족정보삭제
    public void delFamily(EmpFamilyVO params) throws Exception {
        try {
            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long tokenUserNo = userTokenDto.getUserNo();
            params.setModUserNo(tokenUserNo);

            familyModDao.delFamily(params);
        } catch (Exception e) {
            log.error("FamilyService.delFamily Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }
}
