package kr.fingate.gs.auth.role.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.role.dto.RoleDto;
import kr.fingate.gs.auth.role.dto.RoleInfoTopDto;
import kr.fingate.gs.auth.role.dto.RoleItemDto;
import kr.fingate.gs.auth.role.dto.SearchRoleDto;
import kr.fingate.gs.auth.user.dto.UserDto;
import kr.fingate.gs.auth.vo.ItemVO;
import kr.fingate.gs.auth.vo.RoleGroupVO;
import kr.fingate.gs.auth.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    Page<RoleDto> getRoleList(SearchRoleDto searchRoleDto) throws Exception;

    RoleInfoTopDto getRoleInfoTop(Long roleNo) throws Exception;

    List<ItemDto> getRoleItemList(RoleVO roleVO) throws Exception;

    Page<UserDto> getSameRoleUserList(Long roleNo) throws Exception;

    RoleInfoTopDto getGrupRole(Long roleNo) throws Exception;

    Page<RoleDto> getRoleGroupList(SearchRoleDto searchRoleDto) throws Exception;

    RoleInfoTopDto getRoleGroupInfoTop(Long roleGroupNo) throws Exception;

    List<RoleDto> getRoleGroupPrivateList(RoleGroupVO roleGroupVO) throws Exception;

    List<ItemDto> getRoleGroupItemList(RoleGroupVO roleGroupVO) throws Exception;

}
