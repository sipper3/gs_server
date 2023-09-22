package kr.fingate.gs.auth.role.dto;

import com.github.pagehelper.Page;
import kr.fingate.gs.auth.vo.RoleVO;
import kr.fingate.gs.comon.vo.PageVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SearchRoleDto extends PageVO {
    private String searchKey;
    private String searchVal;
    private String searchUseYn;
    private String searchRoleName;
    private long searchRoleNo;

}
