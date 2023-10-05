package kr.fingate.gs.comon.dto.code;

import kr.fingate.gs.comon.vo.OrgnzVO;

import java.util.List;

public class OrgnzDto {
    private long orgnzNo;
    private String orgnzName;
    private String parentDprtmNo;
    private String useYn;
    private List<OrgnzVO> children;
}
