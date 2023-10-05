package kr.fingate.gs.auth.hstry.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.common.dto.hstry.HstryDto;
import kr.fingate.gs.auth.hstry.dto.RoleHstryDto;
import kr.fingate.gs.auth.setup.dto.SearchUserRoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HstryDao {

	List<HstryDto> getRoleHstryList(HstryDto params) throws Exception;
	Page<RoleHstryDto> getUserRoleHstryList(SearchUserRoleDto params);
	Page<RoleHstryDto> getUserRoleGroupHstryList(SearchUserRoleDto params);

}
