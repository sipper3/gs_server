package kr.fingate.gs.auth.role.dto;

import kr.fingate.gs.auth.vo.RoleVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RoleInfoTopDto extends RoleVO {

    private static final long serialVersionUID = -1161306552941923827L;

    // 시스템갯수
    private int systemCodeCnt;

    // 개별권한갯수
    private String idvlRoleCnt;

    // 항목번호리스트
    private List<Long> itemNoLst;

    // 개별롤 리스트
    private List<Long> idvlRoleNoLst;

    // 부여자수
    private String grntNoCnt;

    // 부여조직수
    private String grntOrgnzCnt;
    
}
