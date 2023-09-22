package kr.fingate.gs.auth.item.controller;

import com.github.pagehelper.PageInfo;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.item.dto.SearchItemDto;
import kr.fingate.gs.auth.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/${project.name}/api/item")
@RestController
@RequiredArgsConstructor
public class ItemController {

    final ItemService itemService;

    /**
     * 권한 항목 리스트 조회
     * @param searchItemDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getItemList", method = RequestMethod.POST)
    public PageInfo<ItemDto> getItemList(@RequestBody SearchItemDto searchItemDto) throws Exception {
        return new PageInfo<>(itemService.getItemList(searchItemDto));
    }

    /**
     * 권한 항목 저장
     * @param itemDto
     * @throws Exception
     */
    @RequestMapping(value = "/insItem", method = RequestMethod.POST)
    public void insItem(@RequestBody ItemDto itemDto) throws Exception {
        itemService.insItem(itemDto);
    }

    /**
     * 권한 항목 수정
     * @param updItemDtoLst
     * @throws Exception
     */
    @RequestMapping(value = "/updItem", method = RequestMethod.POST)
    public void updItem(@RequestBody List<ItemDto> updItemDtoLst) throws Exception {
        itemService.updItem(updItemDtoLst);
    }

    /**
     * 권한 항목 삭제
     * @param itemNo
     * @throws Exception
     */
    @RequestMapping(value = "/delItem", method = RequestMethod.POST)
    public void delItem(@RequestParam(required = true) Long itemNo) throws Exception {
        itemService.delItem(itemNo);
    }

}
