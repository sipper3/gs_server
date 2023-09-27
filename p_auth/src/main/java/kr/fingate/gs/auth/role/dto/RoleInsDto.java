package kr.fingate.gs.auth.role.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.auth.vo.RoleItemMapVO;
import kr.fingate.gs.auth.vo.RoleVO;
import kr.fingate.gs.comon.annotation.Info;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RoleInsDto extends RoleVO {

    // 역할번호
    @Info("역할번호")
    private  long roleNo;


    // 역할명
    @Info("역할명")
    private  String roleName;

    // 역할설명
    @Info("역할설명")
    private  String roleDscrp;

    // 사용여부
    @Info("사용여부")
    private  String useYn;

    @Info("롤아이템Map리스트")
    List<RoleItemMapVO> roleItemMapVOList;
}
