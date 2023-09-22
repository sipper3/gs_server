package kr.fingate.gs.auth.setup.dao;

import kr.fingate.gs.auth.setup.dto.UserRoleDto;
import kr.fingate.gs.auth.setup.dto.UserRoleGroupDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleSetupModDao {

    // 사용자 개별롤 저장
    void insUserRole(UserRoleDto params);

    // 사용자 개별롤 수정
    void updUserRole(UserRoleDto params);

    // 사용자 개별롤 저장
    void insUserRoleGroup(UserRoleGroupDto params);

    // 사용자 개별롤 저장
    void updUserRoleGroup(UserRoleGroupDto params);

}
