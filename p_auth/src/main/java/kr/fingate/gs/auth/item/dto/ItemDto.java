package kr.fingate.gs.auth.item.dto;

import kr.fingate.gs.auth.vo.ItemVO;
import kr.fingate.gs.common.annotation.ComparableEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ComparableEntity("GA_ITEM")
public class ItemDto extends ItemVO {

    private long userNo;

}
