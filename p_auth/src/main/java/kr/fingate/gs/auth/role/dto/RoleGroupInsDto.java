package kr.fingate.gs.auth.role.dto;

import kr.fingate.gs.auth.vo.RoleGroupMapVO;
import kr.fingate.gs.auth.vo.RoleGroupVO;
import kr.fingate.gs.auth.vo.RoleItemMapVO;
import kr.fingate.gs.auth.vo.RoleVO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleGroupInsDto extends RoleGroupVO {

    List<RoleGroupMapVO> roleGroupMapList;
}
