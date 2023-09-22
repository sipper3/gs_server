package kr.fingate.gs.auth.role.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.auth.role.dto.*;
import kr.fingate.gs.auth.vo.RoleGroupVO;
import kr.fingate.gs.auth.vo.RoleItemMapVO;
import kr.fingate.gs.auth.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleModDao {

    void insRole(RoleInsDto roleInsDto) throws Exception;

    void updRole(RoleVO roleVO) throws Exception;

    void insRollItemMap(RoleInsDto roleInsDto) throws Exception;

    void updRollItemMap(RoleInsDto roleInsDto) throws Exception;

    void insRoleGroup(RoleGroupInsDto roleGroupInsDto) throws Exception;

    void updRoleGroup(RoleGroupVO roleGroupVO) throws Exception;

    void insRollGroupMap(RoleGroupInsDto roleGroupInsDto) throws Exception;

    void updRollGroupMap(RoleGroupInsDto roleGroupInsDto) throws Exception;

}
