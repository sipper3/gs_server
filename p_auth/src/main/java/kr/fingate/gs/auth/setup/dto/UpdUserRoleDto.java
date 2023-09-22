package kr.fingate.gs.auth.setup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UpdUserRoleDto {

    private long userNo;

    private String reasonCode;
    private String reason;

    private List<UserRoleDto> userRoleList;
    private List<UserRoleGroupDto> userRoleGroupList;

}
