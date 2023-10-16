package kr.fingate.gs.dballot.db.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.core.service.CoreService;
import kr.fingate.gs.dballot.db.dao.DbDao;
import kr.fingate.gs.dballot.db.dto.DbListDto;
import kr.fingate.gs.dballot.db.dto.DbSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DbService {

	final DbDao dbDao;
	final CoreService coreService;

	/**
	 * 개별 롤 리스트 조회
	 */
	public Page<DbListDto> getDbList(DbSearchDto dbSearchDto) throws Exception {

		Page<DbListDto> dbList = new Page<>();

		try {
			PageHelper.startPage(dbSearchDto.getPageNum(), dbSearchDto.getPageSize());
			dbList = dbDao.getDbList(dbSearchDto);

		} catch (BizException e) {
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return dbList;
	}
}
