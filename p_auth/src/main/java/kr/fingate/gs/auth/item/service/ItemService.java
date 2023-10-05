package kr.fingate.gs.auth.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.auth.hstry.service.HstryService;
import kr.fingate.gs.auth.item.dao.ItemDao;
import kr.fingate.gs.auth.item.dao.ItemModDao;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.item.dto.SearchItemDto;
import kr.fingate.gs.auth.util.HistoryUtil;
import kr.fingate.gs.common.consts.CommonConst;
import kr.fingate.gs.common.consts.enums.RedisKey;
import kr.fingate.gs.common.dto.hstry.HstryDto;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    final static Logger logger = LoggerFactory.getLogger(ItemService.class);

    final ItemDao itemDao;
    final ItemModDao itemModDao;
    final HstryService hstryService;
    final RedisUtil redisUtil;

    /**
     * 권한 항목 리스트 조회
     * @param searchItemDto
     * @return
     * @throws Exception
     */
    public Page<ItemDto> getItemList(SearchItemDto searchItemDto) throws Exception {
        Page<ItemDto> itemList = new Page<>();

        UserTokenDto userTokenDto = CoreService.getUserInfo();
        long tokenUserNo = userTokenDto.getUserNo();

        try {
            PageHelper.startPage(searchItemDto.getPageNum(), searchItemDto.getPageSize());
            itemList = itemDao.getItemList(searchItemDto);

        } catch (Exception e) {
            logger.error("ItemService.selItem Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
        return itemList;
    }

    /**
     * 권한 항목 저장
     * @param itemDto
     * @throws Exception
     */
    @Transactional
    public void insItem(ItemDto itemDto) throws Exception {
        try {
            String systemCode = itemDto.getSystemCode();
            String authItemType = itemDto.getAuthItemType();

            int itemTypeNumber = 10000;
            if(authItemType.equals("M")) itemTypeNumber = 20000;
            else if(authItemType.equals("I")) itemTypeNumber = 30000;

            String maxFnctnId = itemDao.getMaxFnctnId(itemDto);    // ex) 001_20003
            String fnctnId = "";

            if (ObjectUtils.isEmpty(maxFnctnId)) {

                if(itemDto.getAuthItemType().equals("S")) { // system 유형
                    // 시스템 Function ID는 각 시스템마다 1개씩만
                    fnctnId = systemCode + "_" + itemTypeNumber;
                } else {
                    fnctnId = systemCode + "_" + (itemTypeNumber + 1);
                }
            } else {

                if(itemDto.getAuthItemType().equals("S")) {
                    throw new BizException("시스템 Function ID는 각 시스템별 1개씩만 생성 가능합니다.");
                }

                int idx = maxFnctnId.indexOf("_");
                int menuItmeSeq = Integer.parseInt(maxFnctnId.substring(idx+2));	// ex) 0003

                if(menuItmeSeq == 9999) {
                    // 메뉴/항목 최대 9999까지
                    throw new BizException("메뉴/항목은 최대 9999개까지 추가 가능합니다.");
                } else {
                    menuItmeSeq += 1;
                }

                fnctnId = systemCode + "_" + (itemTypeNumber + menuItmeSeq);
            }

            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long tokenUserNo = userTokenDto.getUserNo();

            itemDto.setFnctnId(fnctnId);
            itemDto.setRegUserNo(tokenUserNo);

            itemModDao.insItem(itemDto);

            // 변경 이력 저장
            this.insItemHstry(itemDto, null);


        } catch (BizException e) {
            logger.error("ItemService.insItem BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            logger.error("ItemService.insItem Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    /**
     * 권한 항목 수정
     * @param updItemDtoList
     * @throws Exception
     */
    @Transactional
    public void updItem(List<ItemDto> updItemDtoList) throws Exception {

        if(ObjectUtils.isEmpty(updItemDtoList)) return;

        try {

            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long tokenUserNo = userTokenDto.getUserNo();

            List<Long> itemNoList = updItemDtoList.stream()
                    .map(ItemDto::getItemNo)
                    .collect(Collectors.toList());

            SearchItemDto orgSearchItemDto = new SearchItemDto();
            orgSearchItemDto.setItemNoList(itemNoList);

            List<ItemDto> orgItemDtoList = itemDao.getItemList(orgSearchItemDto);

            for(ItemDto itemDto : updItemDtoList) {

                itemDto.setModUserNo(tokenUserNo);

                Optional<ItemDto> orgItemDtoOpt = orgItemDtoList.stream()
                                .filter(f -> f.getItemNo() == itemDto.getItemNo()).findAny();

                itemModDao.updItem(itemDto);

                // 변경 이력 저장
                if(orgItemDtoOpt.isPresent()) {
                    this.insItemHstry(itemDto, orgItemDtoOpt.get());
                } else {
                    this.insItemHstry(itemDto, null);
                }
            }


        } catch (BizException e) {
            logger.error("ItemService.updItem BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            logger.error("ItemService.updItem Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }

    }

    /**
     * 권한 항목 삭제
     * @param itemNo
     * @throws Exception
     */
    @Transactional
    public void delItem(long itemNo) throws Exception {
        try {
            UserTokenDto userTokenDto = CoreService.getUserInfo();
            long tokenUserNo = userTokenDto.getUserNo();

            SearchItemDto orgSearchItemDto = new SearchItemDto();
            orgSearchItemDto.setItemNo(itemNo);

            List<ItemDto> orgItemDtoList = itemDao.getItemList(orgSearchItemDto);

            if(ObjectUtils.isEmpty(orgItemDtoList)) return;

            ItemDto orgItemDto = orgItemDtoList.get(0);

            ItemDto deleteItemDto = new ItemDto();
            BeanUtils.copyProperties(orgItemDto, deleteItemDto);

            deleteItemDto.setModUserNo(tokenUserNo);
            deleteItemDto.setDataState(CommonConst.DATA_STATE_DELETE);
            deleteItemDto.setUseYn(CommonConst.NO);

            itemModDao.updItem(deleteItemDto);

            // 변경 이력 저장
            this.insItemHstry(deleteItemDto, orgItemDto);

            // 레디스 사용자정보 삭제
            redisUtil.delete(redisUtil.getKey(RedisKey.GS_USER_INFO, String.valueOf(userTokenDto.getClientNo()), "*"));

        } catch (BizException e) {
            logger.error("ItemService.delItem BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            logger.error("ItemService.delItem Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }
    }

    private void insItemHstry(ItemDto changeDto, ItemDto orgnlDto) throws Exception {
        UserTokenDto userTokenDto = CoreService.getUserInfo();
        long tokenUserNo = userTokenDto.getUserNo();

        HstryDto hstryDto = new HstryDto();
        hstryDto.setRegUserNo(tokenUserNo);
        hstryDto.setEnttyKey1(changeDto.getItemNo());

        HistoryUtil<ItemDto, HstryDto> historyUtil = new HistoryUtil<>();
        List<HstryDto> hstryList = historyUtil.getHistoryList(changeDto, orgnlDto, hstryDto);

        hstryService.insRoleHstry(hstryList);
    }
}
