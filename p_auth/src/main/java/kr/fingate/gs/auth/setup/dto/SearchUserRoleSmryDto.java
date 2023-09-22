package kr.fingate.gs.auth.setup.dto;

import kr.fingate.gs.comon.vo.PageVO;

import java.util.List;

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
