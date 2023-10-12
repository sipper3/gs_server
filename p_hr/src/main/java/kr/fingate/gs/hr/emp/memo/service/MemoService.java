package kr.fingate.gs.hr.emp.memo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.memo.dao.MemoDao;
import kr.fingate.gs.hr.emp.memo.dao.MemoModDao;
import kr.fingate.gs.hr.emp.memo.dto.MemoDto;
import kr.fingate.gs.hr.vo.EmpMemoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoDao memoDao;
    private final MemoModDao memoModDao;

    // 피고용인 메모정보조회
    public Page<MemoDto> getMemoList(@RequestBody EmpSearchDto empSearchDto) throws Exception {
        try {
            PageHelper.startPage(empSearchDto.getPageNum(), empSearchDto.getPageSize());
            return memoDao.getMemoList(empSearchDto);
        } catch (BizException e) {
            log.error("MemoService.getMemoList BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            log.error("MemoService.getMemoList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 메모정보등록
    public void insMemo(EmpMemoVO params) throws Exception {
        try {
            memoModDao.insMemo(params);
        } catch (Exception e) {
            log.error("MemoService.insMemo Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 메모정보수정
    public void updMemo(EmpMemoVO params) throws Exception {
        try {
            memoModDao.updMemo(params);
        } catch (Exception e) {
            log.error("MemoService.updMemo Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    // 피고용인 메모정보삭제
    public void delMemo(EmpMemoVO params) throws Exception {
        try {
            memoModDao.delMemo(params);
        } catch (Exception e) {
            log.error("MemoService.delMemo Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }
}
