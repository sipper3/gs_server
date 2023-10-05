package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("UserVO")
public class UserVO extends BaseVO {

    // 사용자번호
    private long userNo;

    // 클라이언트번호
    @JsonIgnore
    private long clientNo;

    // 로그인ID
    private String loginId;

    // 사용자명
    private String userName;

    // 조직번호
    private long orgnzNo;

    // 직급코드
    private String pstnCode;

    // 직책코드
    private String dutyCode;

    // 업무코드
    private String bsnsCode;

    // 사용여부
    private String useYn;

}
