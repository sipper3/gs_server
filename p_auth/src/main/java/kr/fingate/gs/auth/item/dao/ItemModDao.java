package kr.fingate.gs.auth.item.dao;

import kr.fingate.gs.auth.item.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemModDao {

	void updItem(ItemDto params) throws Exception;

	void insItem(ItemDto params) throws Exception;

}
