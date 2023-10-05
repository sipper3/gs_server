package kr.fingate.gs.auth.role.controller;

import com.github.pagehelper.Page;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.role.dto.RoleDto;
import kr.fingate.gs.auth.role.dto.RoleInfoTopDto;
import kr.fingate.gs.auth.role.dto.RoleInsDto;
import kr.fingate.gs.auth.role.dto.SearchRoleDto;
import kr.fingate.gs.auth.role.service.RoleService;
import kr.fingate.gs.auth.user.dto.UserDto;
import kr.fingate.gs.auth.vo.RoleItemMapVO;
import kr.fingate.gs.auth.vo.RoleVO;
import kr.fingate.gs.common.dto.PageInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/${project.name}/api/role")
@RestController
@RequiredArgsConstructor
public class RoleController {

    final RoleService roleService;

    /**
     * 개인롤 조회
     * @param searchRoleDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public PageInfoDto<RoleDto> getRoleList(@RequestBody SearchRoleDto searchRoleDto) throws Exception {
        return new PageInfoDto<>(roleService.getRoleList(searchRoleDto));
    }

    /**
     * 롤 저장
     * @param roleInsDto
     * @throws Exception
     */
    @RequestMapping(value = "/insRole", method = RequestMethod.POST)
    public void insRole(@RequestBody RoleInsDto roleInsDto) throws Exception {
        roleService.insRole(roleInsDto);
    }

    /**
     * 롤 수정
     * @param roleVO
     * @throws Exception
     */
    @RequestMapping(value = "/updRole", method = RequestMethod.POST)
    public void updRole(@RequestBody RoleVO roleVO) throws Exception {
        roleService.updRole(roleVO);
    }

    /**
     * 롤 아이템 수정
     * @param roleItemMapList
     * @throws Exception
     */
    @RequestMapping(value = "/updRoleItem", method = RequestMethod.POST)
    public void updRoleItem(@RequestBody List<RoleItemMapVO> roleItemMapList) throws Exception {
        roleService.updRoleItem(roleItemMapList);
    }


    /**
     * 롤 상세 상단
     * @param roleVO
     * @throws Exception
     */
    @RequestMapping(value = "/getRoleInfoTop", method = RequestMethod.POST)
    public RoleInfoTopDto getRoleInfoTop(@RequestBody RoleVO roleVO) throws Exception {
        return roleService.getRoleInfoTop(roleVO);
    }

    /**
     * 롤 상세 아이템 리스트
     * @param roleVO
     * @throws Exception
     */
    @RequestMapping(value = "/getRoleItemList", method = RequestMethod.POST)
    public List<ItemDto> getRoleItemList(@RequestBody RoleVO roleVO) throws Exception {
        return roleService.getRoleItemList(roleVO);
    }

    /**
     * 롤 동일부여 사용자 리스트
     * @param roleVO
     * @throws Exception
     */
    @RequestMapping(value = "/getSameRoleUserList", method = RequestMethod.POST)
    public Page<UserDto> getSameRoleUserList(@RequestBody RoleVO roleVO) throws Exception {
        return roleService.getSameRoleUserList(roleVO);
    }

}
