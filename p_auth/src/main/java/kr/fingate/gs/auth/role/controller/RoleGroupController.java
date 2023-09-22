package kr.fingate.gs.auth.role.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.role.dto.*;
import kr.fingate.gs.auth.role.service.RoleGroupService;
import kr.fingate.gs.auth.role.service.RoleService;
import kr.fingate.gs.auth.user.dto.UserDto;
import kr.fingate.gs.auth.vo.RoleGroupMapVO;
import kr.fingate.gs.auth.vo.RoleGroupVO;
import kr.fingate.gs.auth.vo.RoleItemMapVO;
import kr.fingate.gs.auth.vo.RoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/${project.name}/api/role")
@RestController
@RequiredArgsConstructor
public class RoleGroupController {

    final RoleGroupService roleGroupService;

    /**
     * 그룹롤 조회
     * @param searchRoleDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRoleGroupList", method = RequestMethod.POST)
    public PageInfo<RoleDto> getRoleGroupList(@RequestBody SearchRoleDto searchRoleDto) throws Exception {
        return new PageInfo<>(roleGroupService.getRoleGroupList(searchRoleDto));
    }

    /**
     * 그룹롤 저장
     * @param roleGroupInsDto
     * @throws Exception
     */
    @RequestMapping(value = "/insRoleGroup", method = RequestMethod.POST)
    public void insRoleGroup(@RequestBody RoleGroupInsDto roleGroupInsDto) throws Exception {
        roleGroupService.insRoleGroup(roleGroupInsDto);
    }

    /**
     * 그룹롤 수정
     * @param roleGroupVO
     * @throws Exception
     */
    @RequestMapping(value = "/updRoleGroup", method = RequestMethod.POST)
    public void updRoleGroup(@RequestBody RoleGroupVO roleGroupVO) throws Exception {
        roleGroupService.updRoleGroup(roleGroupVO);
    }

    /**
     * 그룹롤 아이템 수정
     * @param roleGroupMapVOList
     * @throws Exception
     */
    @RequestMapping(value = "/updRoleGroupMap", method = RequestMethod.POST)
    public void updRoleGroupMap(@RequestBody List<RoleGroupMapVO> roleGroupMapVOList) throws Exception {
        roleGroupService.updRoleGroupMap(roleGroupMapVOList);
    }


    /**
     * 그룹롤 상세 상단
     * @param roleGroupVO
     * @throws Exception
     */
    @RequestMapping(value = "/getRoleGroupInfoTop", method = RequestMethod.POST)
    public RoleInfoTopDto getRoleGroupInfoTop(@RequestBody RoleGroupVO roleGroupVO) throws Exception {
        return roleGroupService.getRoleGroupInfoTop(roleGroupVO);
    }

    /**
     * 그룹롤 상세 개인롤 리스트
     * @param roleGroupVO
     * @throws Exception
     */
    @RequestMapping(value = "/getRoleGroupPrivateList", method = RequestMethod.POST)
    public List<RoleDto> getRoleGroupPrivateList(@RequestBody RoleGroupVO roleGroupVO) throws Exception {
        return roleGroupService.getRoleGroupPrivateList(roleGroupVO);
    }

    /**
     * 그룹롤 상세 아이템 리스트
     * @param roleGroupVO
     * @throws Exception
     */
    @RequestMapping(value = "/getRoleGroupItemList", method = RequestMethod.POST)
    public List<ItemDto> getRoleGroupItemList(@RequestBody RoleGroupVO roleGroupVO) throws Exception {
        return roleGroupService.getRoleGroupItemList(roleGroupVO);
    }

//    /**
//     * 롤 동일부여 사용자 리스트
//     * @param roleVO
//     * @throws Exception
//     */
//    @RequestMapping(value = "/getSameRoleUserList", method = RequestMethod.POST)
//    public Page<UserDto> getSameRoleUserList(@RequestBody RoleVO roleVO) throws Exception {
//        return roleService.getSameRoleUserList(roleVO);
//    }

}
