package kr.fingate.gs.auth.role.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRoleDto extends PageVO {

    @Info(value = "searchRoleName", description = "searchkey에 대한 입력값")
    private String searchKey;
    @Info(value = "searchRoleNo", description = "searchkey에 대한 입력값")
    private String searchVal;
    @Info(value = "searchKey", description = "searchkey에 대한 입력값")
    private String searchUseYn;
    @Info(value = "searchVal", description = "searchkey에 대한 입력값")
    private String searchRoleName;
    @Info(value = "searchUseYn", description = "사용여부 : Y, N")
    private long searchRoleNo;

}
