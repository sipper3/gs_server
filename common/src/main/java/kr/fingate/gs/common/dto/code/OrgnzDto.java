package kr.fingate.gs.common.dto.code;

import kr.fingate.gs.common.vo.OrgnzVO;

import java.util.List;

public class OrgnzDto {
    private long orgnzNo;
    private String orgnzName;
    private String parentDprtmNo;
    private String useYn;
    private List<OrgnzVO> children;
}
