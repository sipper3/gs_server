package kr.fingate.gs.auth.setup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchUserRoleDto {

    private long userNo;
    private String roleName;
    private String systemCode;
    private List<Long> roleNoList;
    private List<Long> roleGroupNoList;
}
