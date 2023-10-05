package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Alias("RoleItemMapVO")
public class RoleItemMapVO extends BaseVO {
    //역할번호
    private long roleNo;

    // 항목번호
    private long itemNo;

    // 데이터상태
    private String dataState;
}
