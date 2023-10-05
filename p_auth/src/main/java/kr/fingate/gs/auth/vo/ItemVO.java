package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("ItemVO")
public class ItemVO extends BaseVO {

    // 항목번호
    private long itemNo;

    // 권한항목유형
    private String authItemType;

    // 시스템코드
    private String systemCode;

    // 메뉴명
    private String menuName;

    // 항목명
    private String itemName;

    // 항목설명
    private String itemDscrp;

    // 기능ID
    private String fnctnId;

    // 사용여부
    private String useYn;

    @JsonIgnore
    private String dataState;
}
