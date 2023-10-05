package kr.fingate.gs.auth.setup.dto;

import org.apache.ibatis.type.Alias;

@Alias("SearchUserSameRoleDto")
public class SearchUserSameRoleDto {

    private long roleNo;
    private long roleGroupNo;
    private long orgnzNo;

}
