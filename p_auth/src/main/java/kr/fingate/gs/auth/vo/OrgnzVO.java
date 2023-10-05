package kr.fingate.gs.auth.vo;

import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("OrgnzVO")
public class OrgnzVO extends BaseVO {

    // 조직번호
    private long orgnzNo;

    // 클라이언트번호
    private long clientNo;

    // 조직명
    private String orgnzName;

    // 조직상세명
    private String orgnzDtlName;

    // 부모조직번호
    private long parentDprtmNo;

    // 사용여부
    private String useYn;

}
