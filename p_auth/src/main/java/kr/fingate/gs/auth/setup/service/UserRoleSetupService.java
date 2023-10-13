package kr.fingate.gs.auth.setup.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.auth.hstry.service.HstryService;
import kr.fingate.gs.auth.role.dto.RoleDto;
import kr.fingate.gs.auth.setup.dao.UserRoleSetupDao;
import kr.fingate.gs.auth.setup.dao.UserRoleSetupModDao;
import kr.fingate.gs.auth.setup.dto.*;
import kr.fingate.gs.common.dto.hstry.HstryDto;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.common.util.HistoryUtil;
import kr.fingate.gs.common.util.RedisUtil;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.service.CoreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleSetupService {
    final static Logger logger = LoggerFactory.getLogger(UserRoleSetupService.class);

	final UserRoleSetupDao userRoleSetupDao;
    final UserRoleSetupModDao userRoleSetupModDao;
    final HstryService hstryService;
	final RedisUtil redisUtil;


    /**
     * 개인별 부여/회수 리스트
     * @param searchUserRoleSmryDto
     * @return
     */
    public Page<RoleDto> getUserRoleSmryList(SearchUserRoleSmryDto searchUserRoleSmryDto) {

        Page<RoleDto> userRoleList = new Page<>();

        try {
            PageHelper.startPage(searchUserRoleSmryDto.getPageNum(), searchUserRoleSmryDto.getPageSize());
            userRoleList = userRoleSetupDao.getUserRoleSmryList(searchUserRoleSmryDto);

        } catch (Exception e) {
            logger.error("UserRoleSetupService.getUserRoleSmryList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
        return userRoleList;
    }

    /**
     * 개인별 권한 상세 - System 리스트
     * @param searchUserRoleDto
     * @return
     * @throws Exception
     */
    public List<UserRoleDto> getUserRoleSystemNameList(SearchUserRoleDto searchUserRoleDto) throws Exception {
        return userRoleSetupDao.getUserRoleSystemNameList(searchUserRoleDto);
    }

    /**
     * 개인별 권한 상세 - 개별롤 리스트
     * @param searchUserRoleDto
     * @return
     * @throws Exception
     */
    public List<RoleDto> getUserRoleNameList(SearchUserRoleDto searchUserRoleDto) throws Exception {
        return userRoleSetupDao.getUserRoleNameList(searchUserRoleDto);
    }

    /**
     * 개인 권한 상세 - 그룹롤 리스트
     * @param searchUserRoleDto
     * @return
     * @throws Exception
     */
    public List<RoleDto> getUserRoleGroupNameList(SearchUserRoleDto searchUserRoleDto) throws Exception {
        return userRoleSetupDao.getUserRoleGroupNameList(searchUserRoleDto);
    }

    /**
     * 개별롤 설정 리스트
     * @param searchUserRoleDto
     * @return
     * @throws Exception
     */
    public List<RoleDto> getUserRoleSetupList(SearchUserRoleDto searchUserRoleDto) throws Exception {
        return userRoleSetupDao.getUserRoleSetupList(searchUserRoleDto);
    }

    /**
     * 그룹롤 설정 리스트
     * @param searchUserRoleDto
     * @return
     * @throws Exception
     */
    public List<RoleDto> getUserRoleGroupSetupList(SearchUserRoleDto searchUserRoleDto) throws Exception {
        return userRoleSetupDao.getUserRoleGroupSetupList(searchUserRoleDto);
    }

    /**
     * 동일조직 동일개별롤 부여자
     * @param searchUserSameRoleDto
     * @return
     * @throws Exception
     */
    public List<UserSameRoleDto> getUserSameRoleOrgnzList(SearchUserSameRoleDto searchUserSameRoleDto) throws Exception {
        return userRoleSetupDao.getUserSameRoleOrgnzList(searchUserSameRoleDto);
    }

    /**
     * 동일조직 동일그룹롤 부여자
     * @param searchUserSameRoleDto
     * @return
     * @throws Exception
     */
    public List<UserSameRoleDto> getUserSameRoleGroupOrgnzList(SearchUserSameRoleDto searchUserSameRoleDto) throws Exception {
        return userRoleSetupDao.getUserSameRoleGroupOrgnzList(searchUserSameRoleDto);
    }

    @Transactional
    public void updUserRole(UpdUserRoleDto updUserRoleDto) throws Exception {

        if(ObjectUtils.isEmpty(updUserRoleDto.getUserRoleList())) return;

        UserTokenDto userTokenDto = CoreService.getUserInfo();
        long tokenUserNo = userTokenDto.getUserNo();

        List<Long> roleNoList = updUserRoleDto.getUserRoleList().stream()
                .map(UserRoleDto::getRoleNo)
                .collect(Collectors.toList());

        SearchUserRoleDto pSearchUserRoleDto = new SearchUserRoleDto();
        pSearchUserRoleDto.setUserNo(updUserRoleDto.getUserNo());
        pSearchUserRoleDto.setRoleNoList(roleNoList);
        List<UserRoleDto> dbUserRoleDtoList = userRoleSetupDao.getUserRoleList(pSearchUserRoleDto);

        for (UserRoleDto userRoleDto : updUserRoleDto.getUserRoleList()) {
            userRoleDto.setRegUserNo(tokenUserNo);
            userRoleDto.setModUserNo(tokenUserNo);

            UserRoleDto dbUserRoleDto = null;
            for(Iterator<UserRoleDto> it = dbUserRoleDtoList.iterator(); it.hasNext();){
                UserRoleDto tmpDbUserRoleDto = it.next();

                if(userRoleDto.getRoleNo() == tmpDbUserRoleDto.getRoleNo()) {
                    dbUserRoleDto = new UserRoleDto();
                    BeanUtils.copyProperties(tmpDbUserRoleDto, dbUserRoleDto);
                    it.remove();
                    break;
                }
            }
            
            if(ObjectUtils.isEmpty(dbUserRoleDto)) {
                // insert
                userRoleSetupModDao.insUserRole(userRoleDto);
            } else {
                // update
                userRoleSetupModDao.updUserRole(userRoleDto);
            }
            
            // 변경 이력 저장
            this.insUserRoleHstry(userRoleDto, dbUserRoleDto);
        }
        
        if(!ObjectUtils.isEmpty(dbUserRoleDtoList)) {
            // 기존 DB데이터 삭제 분 처리
            for (UserRoleDto dbUserRoleDto : dbUserRoleDtoList) {

                UserRoleDto newDbUserRoleDto = new UserRoleDto();
                BeanUtils.copyProperties(newDbUserRoleDto, dbUserRoleDto);

                newDbUserRoleDto.setDataState("D");
                newDbUserRoleDto.setModUserNo(tokenUserNo);

                userRoleSetupModDao.updUserRole(newDbUserRoleDto);
                // 변경 이력 저장
                this.insUserRoleHstry(newDbUserRoleDto, dbUserRoleDto);
            }
        }
    }


    private void insUserRoleHstry(UserRoleDto changeDto, UserRoleDto orgnlDto) throws Exception {
        UserTokenDto userTokenDto = CoreService.getUserInfo();
        long tokenUserNo = userTokenDto.getUserNo();

        HstryDto hstryDto = new HstryDto();
        hstryDto.setRegUserNo(tokenUserNo);
        hstryDto.setEnttyKey1(changeDto.getUserNo());
        hstryDto.setEnttyKey2(changeDto.getRoleNo());

        HistoryUtil<UserRoleDto, HstryDto> historyUtil = new HistoryUtil<>();
        List<HstryDto> hstryList = historyUtil.getHistoryList(changeDto, orgnlDto, hstryDto);

        hstryService.insRoleHstry(hstryList);
    }

    @Transactional
    public void updUserRoleGroup(UpdUserRoleDto updUserRoleDto) throws Exception {

        if(ObjectUtils.isEmpty(updUserRoleDto.getUserRoleGroupList())) return;

        UserTokenDto userTokenDto = CoreService.getUserInfo();
        long tokenUserNo = userTokenDto.getUserNo();

        List<Long> roleGroupNoList = updUserRoleDto.getUserRoleGroupList().stream()
                .map(UserRoleGroupDto::getRoleGroupNo)
                .collect(Collectors.toList());

        SearchUserRoleDto pSearchUserRoleDto = new SearchUserRoleDto();
        pSearchUserRoleDto.setUserNo(updUserRoleDto.getUserNo());
        pSearchUserRoleDto.setRoleGroupNoList(roleGroupNoList);
        List<UserRoleGroupDto> dbUserRoleGroupDtoList = userRoleSetupDao.getUserRoleGroupList(pSearchUserRoleDto);

        for (UserRoleGroupDto userRoleGroupDto : updUserRoleDto.getUserRoleGroupList()) {
            userRoleGroupDto.setRegUserNo(tokenUserNo);
            userRoleGroupDto.setModUserNo(tokenUserNo);

            UserRoleGroupDto dbUserRoleGroupDto = null;
            for(Iterator<UserRoleGroupDto> it = dbUserRoleGroupDtoList.iterator(); it.hasNext();){
                UserRoleGroupDto tmpDbUserRoleGroupDto = it.next();

                if(userRoleGroupDto.getRoleNo() == tmpDbUserRoleGroupDto.getRoleNo()) {
                    dbUserRoleGroupDto = new UserRoleGroupDto();
                    BeanUtils.copyProperties(tmpDbUserRoleGroupDto, dbUserRoleGroupDto);
                    it.remove();
                    break;
                }
            }

            if(ObjectUtils.isEmpty(dbUserRoleGroupDto)) {
                // insert
                userRoleSetupModDao.insUserRoleGroup(userRoleGroupDto);
            } else {
                // update
                userRoleSetupModDao.updUserRoleGroup(userRoleGroupDto);
            }

            // 변경 이력 저장
            this.insUserRoleGroupHstry(userRoleGroupDto, dbUserRoleGroupDto);
        }

        if(!ObjectUtils.isEmpty(dbUserRoleGroupDtoList)) {
            // 기존 DB데이터 삭제 분 처리
            for (UserRoleGroupDto dbUserRoleGroupDto : dbUserRoleGroupDtoList) {

                UserRoleGroupDto newDbUserRoleGroupDto = new UserRoleGroupDto();
                BeanUtils.copyProperties(newDbUserRoleGroupDto, dbUserRoleGroupDto);

                newDbUserRoleGroupDto.setDataState("D");
                newDbUserRoleGroupDto.setModUserNo(tokenUserNo);

                userRoleSetupModDao.updUserRoleGroup(newDbUserRoleGroupDto);
                // 변경 이력 저장
                this.insUserRoleGroupHstry(newDbUserRoleGroupDto, dbUserRoleGroupDto);
            }
        }
    }

    private void insUserRoleGroupHstry(UserRoleGroupDto changeDto, UserRoleGroupDto orgnlDto) throws Exception {
        UserTokenDto userTokenDto = CoreService.getUserInfo();
        long tokenUserNo = userTokenDto.getUserNo();

        HstryDto hstryDto = new HstryDto();
        hstryDto.setRegUserNo(tokenUserNo);
        hstryDto.setEnttyKey1(changeDto.getUserNo());
        hstryDto.setEnttyKey2(changeDto.getRoleGroupNo());

        HistoryUtil<UserRoleGroupDto, HstryDto> historyUtil = new HistoryUtil<>();
        List<HstryDto> hstryList = historyUtil.getHistoryList(changeDto, orgnlDto, hstryDto);

        hstryService.insRoleHstry(hstryList);
    }
}
