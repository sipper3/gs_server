package kr.fingate.gs.auth.setup.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Setter
@Getter
@Alias("UpdUserRoleDto")
public class UpdUserRoleDto {

    private long userNo;

    private String reasonCode;
    private String reason;

    private List<UserRoleDto> userRoleList;
    private List<UserRoleGroupDto> userRoleGroupList;

}
