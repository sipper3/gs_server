package kr.fingate.gs.admin.code.dao;

import kr.fingate.gs.admin.code.dto.DispCodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeDao {
    List<DispCodeDto> selDispCode(List<String> params);
}
