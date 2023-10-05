package kr.fingate.gs.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Alias("RoleVO")
public class RoleVO extends BaseVO {

    // 역할번호
    @Info("역할번호")
    private  long roleNo;

    // 클라이언트번호
    @JsonIgnore
    @Info("클라이언트번호")
    private  long clientNo;

    // 역할명
    @Info("역할명")
    private  String roleName;

    // 역할설명
    @Info("역할설명")
    private  String roleDscrp;

    // 사용여부
    @Info("사용여부")
    private  String useYn;

    // 데이터상태
    @JsonIgnore
    @Info("데이터상태")
    private  String dataState;
}
