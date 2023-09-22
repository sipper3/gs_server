package kr.fingate.gs.auth.setup.controller;

import com.github.pagehelper.PageInfo;
import kr.fingate.gs.auth.role.dto.RoleDto;
import kr.fingate.gs.auth.setup.dto.*;
import kr.fingate.gs.auth.setup.service.UserRoleSetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 권한부여/회수 > 개인별부여/회수 Controller
 */
@RequestMapping("/${project.name}/api/setup/user")
@RestController
@RequiredArgsConstructor
public class UserRoleSetupController {

	final UserRoleSetupService userRoleSetupService;

	/**
	 * 개인 권한 리스트
	 * @param searchUserRoleSmryDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userRoleList", method= RequestMethod.POST)
	public PageInfo<RoleDto> getUserRoleSmryList(@RequestBody SearchUserRoleSmryDto searchUserRoleSmryDto) throws Exception {
		return new PageInfo<>(userRoleSetupService.getUserRoleSmryList(searchUserRoleSmryDto));
	}

	/**
	 * 개인 권한 상세 - System 리스트
	 * @param searchUserRoleDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserRoleSystemNameList", method=RequestMethod.POST)
	public List<UserRoleDto> getUserRoleSystemNameList(@RequestBody SearchUserRoleDto searchUserRoleDto) throws Exception {
		return userRoleSetupService.getUserRoleSystemNameList(searchUserRoleDto);
	}

	/**
	 * 개인 권한 상세 - 개별롤 리스트
	 * @param searchUserRoleDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserRoleNameList", method=RequestMethod.POST)
	public List<RoleDto> getUserRoleNameList(@RequestBody SearchUserRoleDto searchUserRoleDto) throws Exception {
		return userRoleSetupService.getUserRoleNameList(searchUserRoleDto);
	}


	/**
	 * 개인 권한 상세 - 그룹롤 리스트
	 * @param searchUserRoleDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserRoleGroupNameList", method=RequestMethod.POST)
	public List<RoleDto> getUserRoleGroupNameList(@RequestBody SearchUserRoleDto searchUserRoleDto) throws Exception {
		return userRoleSetupService.getUserRoleGroupNameList(searchUserRoleDto);
	}

	/**
	 * 개별롤 설정 리스트
	 * @param searchUserRoleDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserRoleSetupList", method=RequestMethod.POST)
	public List<RoleDto> getUserRoleSetupList(@RequestBody SearchUserRoleDto searchUserRoleDto) throws Exception {
		return userRoleSetupService.getUserRoleSetupList(searchUserRoleDto);
	}

	/**
	 * 그룹롤 설정 리스트
	 * @param searchUserRoleDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserRoleGroupSetupList", method=RequestMethod.POST)
	public List<RoleDto> getUserRoleGroupSetupList(@RequestBody SearchUserRoleDto searchUserRoleDto) throws Exception {
		return userRoleSetupService.getUserRoleGroupSetupList(searchUserRoleDto);
	}

	/**
	 * 동일조직 동일개별롤 부여자
	 * @param searchUserSameRoleDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserSameRoleOrgnzList", method=RequestMethod.POST)
	public List<UserSameRoleDto> getUserSameRoleOrgnzList(@RequestBody SearchUserSameRoleDto searchUserSameRoleDto) throws Exception {
		return userRoleSetupService.getUserSameRoleOrgnzList(searchUserSameRoleDto);
	}

	/**
	 * 동일조직 동일그룹롤 부여자
	 * @param searchUserSameRoleDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserSameRoleGroupOrgnzList", method=RequestMethod.POST)
	public List<UserSameRoleDto> getUserSameRoleGroupOrgnzList(@RequestBody SearchUserSameRoleDto searchUserSameRoleDto) throws Exception {
		return userRoleSetupService.getUserSameRoleGroupOrgnzList(searchUserSameRoleDto);
	}

	@RequestMapping(value="/updUserRole", method=RequestMethod.POST)
	public void updUserRole(@RequestBody UpdUserRoleDto updUserRoleDto) throws Exception {

	}

	@RequestMapping(value="/updUserRoleGroup", method=RequestMethod.POST)
	public void updUserRoleGroup(@RequestBody UpdUserRoleDto updUserRoleDto) throws Exception {

	}

}
