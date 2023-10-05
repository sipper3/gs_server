package kr.fingate.gs.auth.item.dto;

import kr.fingate.gs.common.consts.CommonConst;
import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchItemDto extends PageVO {

    private long itemNo;
    private List<String> authItemTypeList;
    private List<String> systemCodeList;
    private List<Long> itemNoList;
    private String searchInput;
    private String searchDateType;        // 기간구분(R:등록일/M:수정일)
    private String startDate;
    private String edDate;


    private String menuName;
    private String itemName;
    private String fnctnId;
    private String systemAplctYn;
    private String itemDscrp;

    private String includeUserInfoYn = CommonConst.YES;
    private String orderByYn = CommonConst.YES;
}
