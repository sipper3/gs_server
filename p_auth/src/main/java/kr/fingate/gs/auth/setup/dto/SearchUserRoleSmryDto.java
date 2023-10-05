package kr.fingate.gs.auth.setup.dto;

import kr.fingate.gs.common.vo.PageVO;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("SearchUserRoleSmryDto")
public class SearchUserRoleSmryDto extends PageVO {

    private long userNo;
    private String userName;
    private String userClass;      // U: 사용자, M:수정자
    private String startDate;
    private String endDate;
    private String orgnzName;
    private List<Long> userNoList;
    private List<Long> roleNoList;
    private List<Long> orgnzNoList;
    private List<String> pstnCodeList;
    private List<String> dutyCodeList;
    private List<String> bsnsCodeList;

}
