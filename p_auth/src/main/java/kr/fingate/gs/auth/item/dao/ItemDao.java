package kr.fingate.gs.auth.item.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.auth.item.dto.ItemDto;
import kr.fingate.gs.auth.item.dto.SearchItemDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemDao {

	Page<ItemDto> getItemList(SearchItemDto params) throws Exception;

	String getMaxFnctnId(ItemDto params) throws Exception;

//	ItemDto getMenuNo(ItemDto params) throws Exception;

}
