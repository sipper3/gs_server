package kr.fingate.gs.auth.role.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.auth.item.dao.ItemDao;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.role.dao.RoleDao;
import kr.fingate.gs.auth.role.dao.RoleModDao;
import kr.fingate.gs.auth.role.dto.*;
import kr.fingate.gs.auth.vo.RoleGroupMapVO;
import kr.fingate.gs.auth.vo.RoleGroupVO;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.service.CoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleGroupService {

	final static Logger logger = LoggerFactory.getLogger(RoleGroupService.class);

	final RoleDao roleDao;
	final RoleModDao roleModDao;
	final ItemDao itemDao;
	final CoreService coreService;


	/**
	 * 그룹 롤 리스트 조회
	 */

	public Page<RoleDto> getRoleGroupList(SearchRoleDto searchRoleDto) throws Exception {

		Page<RoleDto> roleList = new Page<>();

		try {
			PageHelper.startPage(searchRoleDto.getPageNum(), searchRoleDto.getPageSize());
			roleList = roleDao.getRoleGroupList(searchRoleDto);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
		}

		return roleList;
	}


	/**
	 * 그룹롤 상세(상단) 조회
	 */
	public RoleInfoTopDto getRoleGroupInfoTop(RoleGroupVO roleGroupVO) throws Exception {
		RoleInfoTopDto roleInfoTopDto = null;

		try {
			roleInfoTopDto = roleDao.getRoleGroupInfoTop(roleGroupVO.getRoleGroupNo());

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
		}

		return roleInfoTopDto;
	}

	/**
	 * 그룹롤 개인롤리스트 조회
	 */
	public List<RoleDto> getRoleGroupPrivateList(RoleGroupVO roleGroupVO) throws Exception {
		List<RoleDto> roleDtoList = null;

		try {
			roleDtoList = roleDao.getRoleGroupPrivateList(roleGroupVO);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
		}

		return roleDtoList;
	}

	/**
	 * 그룹롤 상세 아이템 리스트 조회
	 */
	public List<ItemDto> getRoleGroupItemList(RoleGroupVO roleGroupVO) throws Exception {
		List<ItemDto> itemDtoList = null;

		try {
			itemDtoList = roleDao.getRoleGroupItemList(roleGroupVO);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
		}

		return itemDtoList;
	}

//	/**
//	 * 개별롤 동일부여 사용자 조회
//	 */
//	public Page<UserDto> getSameRoleUserList(RoleVO roleVO) throws Exception {
//		Page<UserDto> userDtoList = null;
//
//		try {
//			userDtoList = roleDao.getSameRoleUserList(roleVO.getRoleNo());
//
//		} catch (BizException e) {
//			logger.error("ItemService.insItem BizException : {}", e.getMessage());
//			throw new BizException(e.getMessage());
//		} catch (Exception e) {
//			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
//			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
//		}
//
//		return userDtoList;
//	}

	/**
	 * 그룹롤 생성
	 */
	@Transactional(rollbackFor = {BizException.class, Exception.class})
	public void insRoleGroup(RoleGroupInsDto roleGroupInsDto) throws Exception {
		try {

			UserTokenDto userTokenDto = CoreService.getUserInfo();

			roleGroupInsDto.setRegUserNo(userTokenDto.getUserNo());
			roleGroupInsDto.setModUserNo(userTokenDto.getUserNo());
			roleGroupInsDto.setRegUserName(userTokenDto.getUserName());
			roleGroupInsDto.setModUserName(userTokenDto.getUserName());

			roleModDao.insRoleGroup(roleGroupInsDto);

			List<RoleGroupMapVO> roleGroupMapList = roleGroupInsDto.getRoleGroupMapList();

			if(roleGroupMapList == null) throw new BizException("필수값 필요.");

			roleModDao.insRollGroupMap(roleGroupInsDto);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * 그룹롤 수정
	 */
	@Transactional(rollbackFor = {BizException.class, Exception.class})
	public void updRoleGroup(RoleGroupVO roleGroupVO) throws Exception {
		try {

			UserTokenDto userTokenDto = CoreService.getUserInfo();
			roleGroupVO.setModUserNo(userTokenDto.getUserNo());
			roleGroupVO.setModUserName(userTokenDto.getUserName());

			roleModDao.updRoleGroup(roleGroupVO);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * 그룹롤 항목 수정
	 */
	@Transactional(rollbackFor = {BizException.class, Exception.class})
	public void updRoleGroupMap(List<RoleGroupMapVO> roleGroupMapList) throws Exception {
		try {

			RoleGroupInsDto roleGroupInsDto = new RoleGroupInsDto();
			UserTokenDto userTokenDto = CoreService.getUserInfo();

			roleGroupInsDto.setModUserNo(userTokenDto.getUserNo());
			roleGroupInsDto.setModUserName(userTokenDto.getUserName());

			roleGroupInsDto.setRoleGroupMapList(roleGroupMapList);

			if(roleGroupMapList == null) throw new BizException("필수값 필요.");

			roleModDao.updRollGroupMap(roleGroupInsDto);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
		}
	}

}
