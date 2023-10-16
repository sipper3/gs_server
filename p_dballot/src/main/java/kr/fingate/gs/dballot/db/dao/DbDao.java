package kr.fingate.gs.dballot.db.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.dballot.db.dto.DbDstrbListDto;
import kr.fingate.gs.dballot.db.dto.DbInfoDto;
import kr.fingate.gs.dballot.db.dto.DbListDto;
import kr.fingate.gs.dballot.db.dto.DbSearchDto;
import kr.fingate.gs.dballot.vo.DbVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DbDao {

    Page<DbListDto> getDbList(DbSearchDto dbSearchDto) throws Exception;

    DbInfoDto getDbInfo(DbSearchDto dbSearchDto) throws Exception;

    List<DbDstrbListDto> getDstrbList(DbSearchDto dbSearchDto) throws Exception;

    DbVO getDb(long dbNo) throws Exception;

}
