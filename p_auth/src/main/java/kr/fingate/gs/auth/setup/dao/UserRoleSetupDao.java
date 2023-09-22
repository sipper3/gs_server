package kr.fingate.gs.auth.setup.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.role.dto.RoleDto;
import kr.fingate.gs.auth.setup.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleSetupDao {

    // 사용자 권한 조회용 - 항목 전체 리스트
    List<ItemDto> getUserAllRoleItemList(SearchUserRoleDto params);

    List<UserRoleDto> getUserRoleList(SearchUserRoleDto params);

    List<UserRoleGroupDto> getUserRoleGroupList(SearchUserRoleDto params);

    //  사용자역할 리스트
    Page<RoleDto> getUserRoleSmryList(SearchUserRoleSmryDto params);

    // 롤설정 상세 - System 리스트
    List<UserRoleDto> getUserRoleSystemNameList(SearchUserRoleDto params);

    // 롤설정 상세 - 개별롤 리스트
    List<RoleDto> getUserRoleNameList(SearchUserRoleDto params);

    // 롤설정 상세 - 그룰롤 리스트
    List<RoleDto> getUserRoleGroupNameList(SearchUserRoleDto params);

    // 롤설정 상세 - 개별롤 리스트
    List<RoleDto> getUserRoleSetupList(SearchUserRoleDto params);

    // 롤설정 상세 - 그룹롤 리스트
    List<RoleDto> getUserRoleGroupSetupList(SearchUserRoleDto params);

    // 동일조직 개별롤 부여자
    List<UserSameRoleDto> getUserSameRoleOrgnzList(SearchUserSameRoleDto params);

    // 동일조직 그룰롤 부여자
    List<UserSameRoleDto> getUserSameRoleGroupOrgnzList(SearchUserSameRoleDto params);



    
}
