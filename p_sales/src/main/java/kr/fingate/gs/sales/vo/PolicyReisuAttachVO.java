package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PolicyReisuAttachVO extends BaseVO {

    @Info(value="재발행순번")
    private long reisuSeq;

    @Info(value="첨부파일순번")
    private long attachFileSeq;

    @Info(value="원본파일명")
    private String orgnlFileName;

    @Info(value="파일명")
    private String fileName;

    @Info(value="파일경로")
    private String filePath;

    @Info(value="파일크기")
    private int fileSize;

    @Info(value="파일형식")
    private String fileFormat;

    @Info(value="데이터상태")
    private String dataState;

}
