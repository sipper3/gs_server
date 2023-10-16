package kr.fingate.gs.auth.role.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.auth.item.dao.ItemDao;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.role.dao.RoleDao;
import kr.fingate.gs.auth.role.dao.RoleModDao;
import kr.fingate.gs.auth.role.dto.RoleDto;
import kr.fingate.gs.auth.role.dto.RoleInfoTopDto;
import kr.fingate.gs.auth.role.dto.RoleInsDto;
import kr.fingate.gs.auth.role.dto.SearchRoleDto;
import kr.fingate.gs.auth.user.dto.UserDto;
import kr.fingate.gs.auth.vo.RoleItemMapVO;
import kr.fingate.gs.auth.vo.RoleVO;
import kr.fingate.gs.core.aop.exception.BizException;
import kr.fingate.gs.core.aop.response.ResponseCode;
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
public class RoleService {

	final static Logger logger = LoggerFactory.getLogger(RoleService.class);

	final RoleDao roleDao;
	final RoleModDao roleModDao;
	final ItemDao itemDao;
	final CoreService coreService;


	/**
	 * 개별 롤 리스트 조회
	 */

	public Page<RoleDto> getRoleList(SearchRoleDto searchRoleDto) throws Exception {

		Page<RoleDto> roleList = new Page<>();

		try {
			PageHelper.startPage(searchRoleDto.getPageNum(), searchRoleDto.getPageSize());
			roleList = roleDao.getRoleList(searchRoleDto);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
		}

		return roleList;
	}


	/**
	 * 개별롤 상세(상단) 조회
	 */
	public RoleInfoTopDto getRoleInfoTop(RoleVO roleVO) throws Exception {
		RoleInfoTopDto roleInfoTopDto = null;

		try {
			roleInfoTopDto = roleDao.getRoleInfoTop(roleVO.getRoleNo());

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
		}

		return roleInfoTopDto;
	}

	/**
	 * 개별롤 상세(롤리스트) 조회
	 */
	public List<ItemDto> getRoleItemList(RoleVO roleVO) throws Exception {
		List<ItemDto> itemDtoList = null;

		try {
			itemDtoList = roleDao.getRoleItemList(roleVO);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
		}

		return itemDtoList;
	}

	/**
	 * 개별롤 동일부여 사용자 조회
	 */
	public Page<UserDto> getSameRoleUserList(RoleVO roleVO) throws Exception {
		Page<UserDto> userDtoList = null;

		try {
			userDtoList = roleDao.getSameRoleUserList(roleVO.getRoleNo());

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
		}

		return userDtoList;
	}

	/**
	 * 개별롤 생성
	 */
	@Transactional(rollbackFor = {BizException.class, Exception.class})
	public void insRole(RoleInsDto roleInsDto) throws Exception {
		try {

			UserTokenDto userTokenDto = CoreService.getUserInfo();

			roleInsDto.setRegUserNo(userTokenDto.getUserNo());
			roleInsDto.setModUserNo(userTokenDto.getUserNo());
			roleInsDto.setRegUserName(userTokenDto.getUserName());
			roleInsDto.setModUserName(userTokenDto.getUserName());

			roleModDao.insRole(roleInsDto);

			List<RoleItemMapVO> roleItemMapList = roleInsDto.getRoleItemMapList();

			if(roleItemMapList == null) throw new BizException("필수값 필요.");

			roleModDao.insRollItemMap(roleInsDto);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * 개별롤 수정
	 */
	@Transactional(rollbackFor = {BizException.class, Exception.class})
	public void updRole(RoleVO roleVO) throws Exception {
		try {

			UserTokenDto userTokenDto = CoreService.getUserInfo();

			roleVO.setModUserNo(userTokenDto.getUserNo());
			roleVO.setModUserName(userTokenDto.getUserName());

			roleModDao.updRole(roleVO);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * 개별롤 항목(아이템) 수정
	 */
	@Transactional(rollbackFor = {BizException.class, Exception.class})
	public void updRoleItem(List<RoleItemMapVO> roleItemMapList) throws Exception {
		try {

			RoleInsDto roleInsDto = new RoleInsDto();
			UserTokenDto userTokenDto = CoreService.getUserInfo();

			roleInsDto.setModUserNo(userTokenDto.getUserNo());
			roleInsDto.setModUserName(userTokenDto.getUserName());

			roleInsDto.setRoleItemMapList(roleItemMapList);

			if(roleItemMapList == null) throw new BizException("필수값 필요.");

			roleModDao.updRollItemMap(roleInsDto);

		} catch (BizException e) {
			logger.error("ItemService.insItem BizException : {}", e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
			throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
		}
	}

}
