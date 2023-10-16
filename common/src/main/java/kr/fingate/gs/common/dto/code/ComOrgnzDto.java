package kr.fingate.gs.common.dto.code;

import kr.fingate.gs.common.vo.ComOrgnzVO;

import java.util.List;

public class ComOrgnzDto {
    private long orgnzNo;
    private String orgnzName;
    private String parentDprtmNo;
    private String useYn;
    private List<ComOrgnzVO> children;
}
