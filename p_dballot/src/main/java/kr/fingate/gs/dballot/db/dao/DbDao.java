package kr.fingate.gs.dballot.db.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.dballot.db.dto.DbListDto;
import kr.fingate.gs.dballot.db.dto.DbSearchDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbDao {

    Page<DbListDto> getDbList(DbSearchDto dbSearchDto) throws Exception;

}
