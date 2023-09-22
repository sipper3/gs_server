package kr.fingate.gs.auth.hstry.dao;

import kr.fingate.gs.comon.dto.hstry.HstryDto;
import kr.fingate.gs.auth.hstry.dto.ReasonDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HstryModDao {

	void insRoleHstry(List<HstryDto> list);
	void insDataHstry(List<HstryDto> list);
	void insRoleReason(ReasonDto params);
	void insDataReason(ReasonDto params);

}
