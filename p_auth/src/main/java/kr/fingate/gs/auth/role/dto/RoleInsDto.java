package kr.fingate.gs.auth.role.dto;

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
public class RoleInsDto extends RoleVO {

    @Info("롤아이템Map리스트")
    List<RoleItemMapVO> roleItemMapVOList;

    @Info("아이템 리스트")
    List<Integer> itemNos;
}
